package com.digimenu.main.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.digimenu.main.domain.converter.CartEntityConverter;
import com.digimenu.main.domain.converter.TableOrderDtoConverter;
import com.digimenu.main.domain.converter.TableOrdersEntityConverter;
import com.digimenu.main.domain.dto.TableOrderDto;
import com.digimenu.main.domain.entity.Cart;
import com.digimenu.main.domain.entity.Menu;
import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.domain.response.CreateOrderResponse;
import com.digimenu.main.service.CartService;
import com.digimenu.main.service.MenuService;
import com.digimenu.main.service.RestaurantService;
import com.digimenu.main.service.Table_OrdersService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digimenu.main.domain.entity.Table_Orders;
import com.digimenu.main.repository.Table_OrdersRepository;
@Service
public class Table_OrdersServiceImpl implements Table_OrdersService {

	private Table_OrdersRepository tor;
	private RestaurantService restaurantService;
	private MenuService menuService;
	private CartService cartService;


	@Autowired
	public Table_OrdersServiceImpl(Table_OrdersRepository tor, RestaurantService restaurantService, MenuService menuService,CartService cartService) {
		this.tor = tor;
		this.restaurantService = restaurantService;
		this.menuService = menuService;
		this.cartService=cartService;
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
	public CreateOrderResponse createOrder(TableOrderDto tableOrderDto){

		final Restaurant restaurant=restaurantService.getRestaurant(tableOrderDto.getResId());	//table-orderda set etmek için çektik
		final List<Table_Orders> tableOrdersList=new ArrayList<>();
		final List<Cart> cartList=new ArrayList<>();
		final CreateOrderResponse response=new CreateOrderResponse();
		tableOrderDto.getItems().forEach(item->{
			tableOrdersList.add(TableOrdersEntityConverter.convert(item,tableOrderDto.getMasaNo(),restaurant));
			cartList.add(CartEntityConverter.convert(item,tableOrderDto.getMasaNo(),tableOrderDto.getResId()));

		});
		tor.saveAll(tableOrdersList);
		cartService.saveAllCart(cartList);
		response.setSocketMessage(makeSocketString(tableOrderDto.getMasaNo(),cartList));
		response.setRestaurantOwner(restaurantService.getRestaurant(tableOrderDto.getResId()).getOwner().getUsername());
		return response;
	}

	@Override
	public void insertOrder(Long id,Long res, Integer masa) {
		tor.insertTable_Order(id.intValue(),res, masa);
	}

	private String makeSocketString(Integer masaNo,List<Cart> cartList){
		StringBuilder sb=new StringBuilder(); //daha sonra formatla oynamak icin stringbuilder
		sb.append("MASA:" + masaNo + " YENi SİPARİŞ ! <br> " );
		cartList.forEach(item->{
			sb.append("<br>" + " <b> <font color='#ce2865'> "+ item.getItem() + " </font> </b> ");
			if(!item.getMessage().isEmpty()){
				sb.append(" --> mesaj: " + item.getMessage());
			}
		});
		return sb.toString();
	}

}
