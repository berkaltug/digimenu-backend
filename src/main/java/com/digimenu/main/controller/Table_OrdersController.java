package com.digimenu.main.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digimenu.main.entity.Table_Orders;
import com.digimenu.main.service.Table_OrdersService;

@RestController
@RequestMapping("/table_orders")
public class Table_OrdersController {
	
	@Autowired
	private Table_OrdersService table_ordersService;

	
	@GetMapping
	Collection<Table_Orders> getAllTableOrders(){
		return table_ordersService.getTable_Orders();
	}
	
	@GetMapping("{restaurant}/{masa}")
	Collection<Table_Orders> getTableOrders(@PathVariable("restaurant") Long id,@PathVariable("masa")Integer masaNo){
		return table_ordersService.getByTableNo(masaNo);
	}
	
	@PostMapping("{restaurant}/{masa}")
	@ResponseStatus(HttpStatus.CREATED)
	Collection<Table_Orders> createTableOrder(@RequestBody Collection<Table_Orders> orders){
		orders.forEach(o->table_ordersService.addTable_Order(o));
		return orders;
	}
}
