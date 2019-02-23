package com.digimenu.main.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digimenu.main.entity.Cart;
import com.digimenu.main.entity.Table_Orders;
import com.digimenu.main.service.CartService;
import com.digimenu.main.service.Table_OrdersService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cs;
	@Autowired 
	private Table_OrdersService tos;
	
	@GetMapping("/{restaurant}/{masa}")
	public List<Cart> getCart(@PathVariable("restaurant") Long resId,@PathVariable("masa")Integer masaNo) {
		return cs.getCart(resId, masaNo);
	}
}
