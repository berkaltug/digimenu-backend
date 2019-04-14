package com.digimenu.main.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digimenu.main.entity.Cart;
import com.digimenu.main.entity.Menu;
import com.digimenu.main.entity.Restaurant;
import com.digimenu.main.entity.Table_Orders;
import com.digimenu.main.service.CartService;
import com.digimenu.main.service.MenuService;
import com.digimenu.main.service.RestaurantService;
import com.digimenu.main.service.Table_OrdersService;


@RestController
@RequestMapping("/table_orders")
public class Table_OrdersController {
	
	
	@Autowired
	private Table_OrdersService table_ordersService;
	@Autowired
	private RestaurantService restaurantService;
	@Autowired 
	private MenuService menuService;
	@Autowired
	private CartService cartService;
	@Autowired
	SimpMessagingTemplate msgTemplate;
	
	
	@GetMapping
	Collection<Table_Orders> getAllTableOrders(){
		return table_ordersService.getTable_Orders();
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")  //test amaçlı fonk
	@GetMapping("{restaurant}/{masa}")
	Collection<Table_Orders> getTableOrders(@PathVariable("restaurant") Long id,@PathVariable("masa")Integer masaNo){
		//return table_ordersService.getByTableNo(masaNo);
		List<Table_Orders> orders =table_ordersService.getTable_Orders();
		List<Table_Orders> resorders=orders.stream().filter(o->o.getRestaurant().getId().equals(id)).collect(Collectors.toList());
		List<Table_Orders> masaorders=resorders.stream().filter(o->o.getMasa().equals(masaNo)).collect(Collectors.toList());
		
		return masaorders;
	}
	
	@PreAuthorize("hasRole('USER') OR hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@PostMapping("{restaurant}/{masa}")
	@ResponseStatus(HttpStatus.CREATED)
	void createTableOrder(@PathVariable("restaurant") Long id,@PathVariable("masa")Integer masaNo,@RequestBody String ordersStr) throws ParseException{
		
		Restaurant res=restaurantService.getRestaurant(id);	//table-orderda set etmek için çektik
		List<Long> itemIds=new ArrayList<Long>();
		JSONParser parser=new JSONParser();
		JSONArray arr=(JSONArray)parser.parse(ordersStr);
		
		//burda gelen cevaptaki bütün itemlerin idsini çekiyoruz
		for(int i=0;i<arr.size();i++) { 	
			JSONObject jsonObject = (JSONObject) arr.get(i);
			itemIds.add((Long)jsonObject.get("id"));	
		}
		
		//her item idsi için tableorder set edip carta da ekleyip dbye gönderiyoruz
		//CrudRepo daki saveAll ile yapmayı dene
		//siparis mesajı hazırlıyoruz
		StringBuilder sb=new StringBuilder(); //daha sonra formatla oynamak icin stringbuilder
		sb.append("MASA:" + masaNo +" YENi SİPARİŞ ! <br>" );
		
		itemIds.forEach(i->{		 
			Table_Orders to=new Table_Orders();
			to.setRestaurant(res);
			to.setMasa(masaNo);
			Menu item=menuService.getMenuItem(i);
			to.setItem(item.getItem()); //string , ismi atanıyor
			to.setPrice(item.getPrice());
			table_ordersService.addTable_Order(to); 
			//ilerde performans için to'ları topluca save fonksiyonuna gödermeyi dene db-java arasında tek sorgu gitsin
			//simdi cart tablosuna da eklicez manuel yapıcaz db triggerı ile yapınca thymeleaf ile cartı çekerken item isimleri fk gözüküyor
			Cart cart=new Cart();
			cart.setItem(item.getItem()); //menu nesnesinin isim kısmını set ettik
			cart.setTotalPrice(item.getPrice());
			cart.setRestaurantId(res.getId());
			cart.setMasaNo(masaNo);
			cartService.addCart(cart);
			// websocket mesajı oluşturuyoruz
			sb.append(item.getItem() + "<br>");
			
		});
		
		
		String msg=sb.toString();
		this.msgTemplate.convertAndSendToUser(res.getOwner().getUsername(), "/restaurant/message", msg);
		
	}
}
