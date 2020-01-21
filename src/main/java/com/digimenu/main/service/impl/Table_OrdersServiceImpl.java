package com.digimenu.main.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.digimenu.main.domain.converter.CartEntityConverter;
import com.digimenu.main.domain.converter.TableOrderDtoConverter;
import com.digimenu.main.domain.converter.TableOrdersEntityConverter;
import com.digimenu.main.domain.dto.TableOrderDto;
import com.digimenu.main.domain.entity.Cart;
import com.digimenu.main.domain.entity.Menu;
import com.digimenu.main.domain.entity.Restaurant;
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

import com.digimenu.main.domain.entity.Table_Orders;
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


    @Autowired
    public Table_OrdersServiceImpl(Table_OrdersRepository tor, RestaurantService restaurantService, MenuService menuService, CartService cartService, SecurityService securityService, UserService userService) {
        this.tor = tor;
        this.restaurantService = restaurantService;
        this.menuService = menuService;
        this.cartService = cartService;
        this.securityService = securityService;
        this.userService = userService;
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
//        if(!checkLocation(tableOrderDto.getLatitude(),tableOrderDto.getLongitude(),restaurant.getLatitude(),restaurant.getLongitude(), restaurant.getRadius())){
//            return Optional.empty();
//        }
        final List<Table_Orders> tableOrdersList = new ArrayList<>();
        final List<Cart> cartList = new ArrayList<>();
        final CreateOrderResponse response = new CreateOrderResponse();
        final User user = userService.findByUsername(securityService.findLoggedInUsername());
        tableOrderDto.getItems().forEach(item -> {
            tableOrdersList.add(TableOrdersEntityConverter.convert(item, tableOrderDto.getMasaNo(), restaurant, user));
            cartList.add(CartEntityConverter.convert(item, tableOrderDto.getMasaNo(), tableOrderDto.getResId()));

        });
        tor.saveAll(tableOrdersList);
        cartService.saveAllCart(cartList);
        response.setSocketMessage(makeSocketString(tableOrderDto.getMasaNo(), cartList));
        response.setRestaurantOwner(restaurantService.getRestaurant(tableOrderDto.getResId()).getOwner().getUsername());
        return Optional.of(response);
    }

    @Override
    public ReportResponse getReport(Date startdate, Date endate) {
        ReportResponse response = new ReportResponse();
        response.setReportList(tor.getSellReport(restaurantService.getRestaurant(1l),
                startdate,
                endate)
        );
        return response;
    }

    @Override
    public void deleteWrongTableOrder(Restaurant res, String name, Integer masaNo) {
        tor.deleteWrongOrder(res, name, masaNo);
    }


    private String makeSocketString(Integer masaNo, List<Cart> cartList) {
        StringBuilder sb = new StringBuilder(); //daha sonra formatla oynamak icin stringbuilder
        sb.append("MASA:" + masaNo + " YENi SİPARİŞ ! <br> ");
        cartList.forEach(item -> {
            sb.append("<br>" + " <b> <font color='#ce2865'> " + item.getItem() + " </font> </b> ");
            if (!item.getMessage().isEmpty()) {
                sb.append(" --> mesaj: " + item.getMessage());
            }
        });
        return sb.toString();
    }

    private boolean checkLocation(Double x1,Double y1,Double x2,Double y2,Double radius){
        Double distance=sqrt(pow((x2-x1),2) + pow((y2-y1),2));
        return distance <= radius;
    }

}
