package com.digimenu.main.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.digimenu.main.domain.converter.MessageDtoConverter;
import com.digimenu.main.domain.converter.TableOrderDtoConverter;
import com.digimenu.main.domain.converter.WaitressDtoConverter;
import com.digimenu.main.domain.request.LocationRequest;
import com.digimenu.main.domain.request.TableOrderRequest;
import com.digimenu.main.domain.response.CallWaitressResponse;
import com.digimenu.main.domain.response.CreateOrderResponse;
import com.digimenu.main.domain.response.PastOrdersResponse;
import com.digimenu.main.service.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


@RestController
@RequestMapping("/table_orders")
public class Table_OrdersController {


    private Table_OrdersService table_ordersService;
    private RestaurantService restaurantService;
    private SimpMessagingTemplate msgTemplate;

    @Autowired
    public Table_OrdersController(Table_OrdersService table_ordersService, RestaurantService restaurantService, SimpMessagingTemplate msgTemplate) {
        this.table_ordersService = table_ordersService;
        this.restaurantService = restaurantService;
        this.msgTemplate = msgTemplate;
    }

    @GetMapping
    Collection<Table_Orders> getAllTableOrders() {
        return table_ordersService.getTable_Orders();
    }

    @PreAuthorize("hasRole('USER') OR hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @PostMapping("{restaurant}/{masa}")
    public ResponseEntity<String> createTableOrder(@PathVariable("restaurant") Long id, @PathVariable("masa") Integer masaNo, @RequestBody TableOrderRequest request) {
        Optional<CreateOrderResponse> response = table_ordersService.createOrder(TableOrderDtoConverter.convert(request, id, masaNo));
        if(response.isPresent()) {
            this.msgTemplate.convertAndSendToUser(response.get().getRestaurantOwner(), "/restaurant/message", response.get().getSocketMessage());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Location Error !", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('RESTAURANT') or hasRole('ADMIN')")
    @PostMapping("/garson/{restaurant}/{masa}")
    public ResponseEntity<String> callWaiter(@PathVariable("restaurant") Long restaurantId, @PathVariable("masa") Integer masaNo, @RequestBody LocationRequest request) {
        Optional<CallWaitressResponse> response = table_ordersService.callWaitress(WaitressDtoConverter.convert(request, restaurantId, masaNo));
        if(response.isPresent()){
            this.msgTemplate.convertAndSendToUser(response.get().getUsername(), "/restaurant/message", response.get().getMessageDto());
            return  new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Location Error !", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('USER') OR hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @GetMapping("/pastOrders")
    public ResponseEntity<PastOrdersResponse> getPastOrders(){
        PastOrdersResponse response = table_ordersService.findUsersPastOrders();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
