package com.digimenu.main.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.digimenu.main.domain.converter.TableOrderDtoConverter;
import com.digimenu.main.domain.request.TableOrderRequest;
import com.digimenu.main.domain.response.CreateOrderResponse;
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

import com.digimenu.main.domain.entity.Cart;
import com.digimenu.main.domain.entity.Menu;
import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.domain.entity.Table_Orders;
import com.digimenu.main.service.CartService;
import com.digimenu.main.service.MenuService;
import com.digimenu.main.service.RestaurantService;
import com.digimenu.main.service.Table_OrdersService;


@RestController
@RequestMapping("/table_orders")
public class Table_OrdersController {


    private Table_OrdersService table_ordersService;
    private RestaurantService restaurantService;
    private MenuService menuService;
    private CartService cartService;
    SimpMessagingTemplate msgTemplate;

    @Autowired
    public Table_OrdersController(Table_OrdersService table_ordersService, RestaurantService restaurantService, MenuService menuService, CartService cartService, SimpMessagingTemplate msgTemplate) {
        this.table_ordersService = table_ordersService;
        this.restaurantService = restaurantService;
        this.menuService = menuService;
        this.cartService = cartService;
        this.msgTemplate = msgTemplate;
    }

    @GetMapping
    Collection<Table_Orders> getAllTableOrders() {
        return table_ordersService.getTable_Orders();
    }

    @PreAuthorize("hasRole('ADMIN')")  //test amaçlı fonk
    @GetMapping("{restaurant}/{masa}")
    Collection<Table_Orders> getTableOrders(@PathVariable("restaurant") Long id, @PathVariable("masa") Integer masaNo) {
        //return table_ordersService.getByTableNo(masaNo);
        List<Table_Orders> orders = table_ordersService.getTable_Orders();
        List<Table_Orders> resorders = orders.stream().filter(o -> o.getRestaurant().getId().equals(id)).collect(Collectors.toList());
        List<Table_Orders> masaorders = resorders.stream().filter(o -> o.getMasa().equals(masaNo)).collect(Collectors.toList());

        return masaorders;
    }

    @PreAuthorize("hasRole('USER') OR hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @PostMapping("{restaurant}/{masa}")
    @ResponseStatus(HttpStatus.CREATED)
    void createTableOrder(@PathVariable("restaurant") Long id, @PathVariable("masa") Integer masaNo, @RequestBody TableOrderRequest request) {
        CreateOrderResponse response = table_ordersService.createOrder(TableOrderDtoConverter.convert(request, id, masaNo));
        this.msgTemplate.convertAndSendToUser(response.getRestaurantOwner(), "/restaurant/message", response.getSocketMessage());
    }


    @PreAuthorize("hasRole('USER') or hasRole('RESTAURANT') or hasRole('ADMIN')")
    @PostMapping("/garson/{restaurant}/{masa}")
    public void callWaiter(@PathVariable("restaurant") Long restaurantId, @PathVariable("masa") Integer masaNo) {
        Restaurant res = restaurantService.getRestaurant(restaurantId);
        StringBuilder sb = new StringBuilder();
        sb.append("Garson Bekleniyor ! Lütfen <h6>" + masaNo + " </h6> Numaralı Masayla İlgileniniz.");
        this.msgTemplate.convertAndSendToUser(res.getOwner().getUsername(), "/restaurant/message", sb.toString());
    }
}
