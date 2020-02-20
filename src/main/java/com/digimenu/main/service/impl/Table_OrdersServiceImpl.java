package com.digimenu.main.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.digimenu.main.domain.converter.CartEntityConverter;
import com.digimenu.main.domain.converter.MessageDtoConverter;
import com.digimenu.main.domain.converter.TableOrderDtoConverter;
import com.digimenu.main.domain.converter.TableOrdersEntityConverter;
import com.digimenu.main.domain.dto.CallWaitressDto;
import com.digimenu.main.domain.dto.MessageDto;
import com.digimenu.main.domain.dto.TableOrderDto;
import com.digimenu.main.domain.entity.*;
import com.digimenu.main.domain.response.CallWaitressResponse;
import com.digimenu.main.domain.response.CreateOrderResponse;
import com.digimenu.main.domain.response.ReportResponse;
import com.digimenu.main.security.User;
import com.digimenu.main.service.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digimenu.main.repository.Table_OrdersRepository;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

@Service
public class Table_OrdersServiceImpl implements Table_OrdersService {

    private Table_OrdersRepository tor;
    private RestaurantService restaurantService;
    private MenuService menuService;
    private CartService cartService;
    private SecurityService securityService;
    private UserService userService;
    private MapsService mapsService;
    private WebsocketMessageService websocketMessageService;

    @Autowired
    public Table_OrdersServiceImpl(Table_OrdersRepository tor, RestaurantService restaurantService, MenuService menuService, CartService cartService, SecurityService securityService, UserService userService, MapsService mapsService, WebsocketMessageService websocketMessageService) {
        this.tor = tor;
        this.restaurantService = restaurantService;
        this.menuService = menuService;
        this.cartService = cartService;
        this.securityService = securityService;
        this.userService = userService;
        this.mapsService = mapsService;
        this.websocketMessageService = websocketMessageService;
    }

    @Override
    public Table_Orders getTable_Order(Long id) {
        return tor.getOne(id);
    }

    @Override
    public List<Table_Orders> getByTableNo(Integer no) {
        return tor.getByMasaNo(no);
    }

    @Override
    public List<Table_Orders> getTable_Orders() {
        return tor.findAll();
    }

    @Override
    public void deleteTable_Order(Long id) {
        tor.delete(tor.getOne(id));
    }

    @Override
    public void addTable_Order(Table_Orders tableorders) {
        tor.save(tableorders);
    }

    @Override
    public Optional<CreateOrderResponse> createOrder(TableOrderDto tableOrderDto) {

        final Restaurant restaurant = restaurantService.getRestaurant(tableOrderDto.getResId());
        if(!mapsService.checkHaversineDistance(tableOrderDto.getLatitude(),tableOrderDto.getLongitude(),restaurant.getLatitude(),restaurant.getLongitude(), restaurant.getRadius())){
            return Optional.empty();
        }

        final List<Table_Orders> tableOrdersList = new ArrayList<>();
        final List<Cart> cartList = new ArrayList<>();
        final CreateOrderResponse response = new CreateOrderResponse();
        final User user = userService.findByUsername(securityService.findLoggedInUsername());
        String tableName = "";
        Optional<TableName> tableNameEntity = restaurantService.getTableName(restaurant, tableOrderDto.getMasaNo());
        if(tableNameEntity.isPresent()){
            tableName="[ " + tableNameEntity.get().getName() + " ]";
        }
        tableOrderDto.getItems().forEach(item -> {
            tableOrdersList.add(TableOrdersEntityConverter.convert(item, tableOrderDto.getMasaNo(), restaurant, user));
            cartList.add(CartEntityConverter.convert(item, tableOrderDto.getMasaNo(), tableOrderDto.getResId()));

        });
        tor.saveAll(tableOrdersList);
        cartService.saveAllCart(cartList);
        response.setSocketMessage(makeMessageDto(tableOrderDto.getMasaNo(),tableName,cartList,restaurant.getId()));
        response.setRestaurantOwner(restaurant.getOwner().getUsername());
        return Optional.of(response);
    }

    @Override
    public ReportResponse getReport(Timestamp startdate, Timestamp endate) {
        ReportResponse response = new ReportResponse();
        response.setReportList(tor.getSellReport(restaurantService.getLoggedInRestaurant(),
                startdate,
                endate)
        );
        return response;
    }

    @Override
    public void deleteWrongTableOrder(Restaurant res, String name, Integer masaNo) {
        tor.deleteWrongOrder(res.getId(), name, masaNo);
    }

    @Override
    public Optional<CallWaitressResponse> callWaitress(CallWaitressDto dto){
        final Restaurant restaurant=restaurantService.getRestaurant(dto.getRestaurantId());
        if(!mapsService.checkHaversineDistance(dto.getLatitude(),dto.getLongitude(),restaurant.getLatitude(),restaurant.getLongitude(), restaurant.getRadius())){
            return Optional.empty();
        }else{
            CallWaitressResponse response = new CallWaitressResponse();
            response.setMessageDto(makeMessageDto(dto.getMasaNo(),dto.getRestaurantId()));
            response.setUsername(restaurant.getOwner().getUsername());
            return Optional.of(response);
        }
    }

    private MessageDto makeMessageDto(Integer masaNo,String tableName,List<Cart> cartList,Long resId){
        WebsocketMessage message=new WebsocketMessage();
        message.setMessage(makeSocketString(masaNo,tableName, cartList));
        message.setRestaurantId(resId);
        message.setMasaNo(masaNo);
        WebsocketMessage insertedMessage =websocketMessageService.insertMessage(message);
        return MessageDtoConverter.convert(insertedMessage);
    }

    private MessageDto makeMessageDto(Integer masaNo,Long resId){
        WebsocketMessage message=new WebsocketMessage();
        message.setMessage(makeWaitressMessage(masaNo));
        message.setRestaurantId(resId);
        message.setMasaNo(masaNo);
        WebsocketMessage insertedMessage =websocketMessageService.insertMessage(message);
        return MessageDtoConverter.convert(insertedMessage);
    }


    private String makeSocketString(Integer masaNo,String tableName, List<Cart> cartList) {
        StringBuilder sb = new StringBuilder(); //daha sonra formatla oynamak icin stringbuilder
        sb.append("MASA:" + masaNo + " "+ tableName +"  YENi SİPARİŞ ! <br> ");
        cartList.forEach(item -> {
            sb.append("<br>" + " <b> <font color='#ce2865'> " + item.getItem() + " </font> </b> ");
            if (!item.getMessage().isEmpty()) {
                sb.append(" --> mesaj: " + item.getMessage());
            }
        });
        return sb.toString();
    }

    private String makeWaitressMessage(Integer masaNo){
        StringBuilder sb = new StringBuilder();
        sb.append("Garson Bekleniyor ! Lütfen <h6>" + masaNo + " </h6> Numaralı Masayla İlgileniniz.");
        return sb.toString();
    }


}
