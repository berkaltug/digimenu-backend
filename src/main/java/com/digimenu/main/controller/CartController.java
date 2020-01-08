package com.digimenu.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digimenu.main.domain.entity.Cart;
import com.digimenu.main.service.CartService;

@RestController
@RequestMapping("/restaurant")
@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_RESTAURANT')")
public class CartController {

	private CartService cs;

	@Autowired
	public CartController(CartService cs) {
		this.cs = cs;
	}

	@GetMapping("cart/{restaurant}/{masa}")
	public List<Cart> getCart(@PathVariable("restaurant") Long resId,@PathVariable("masa")Integer masaNo) {
		return cs.getCart(resId, masaNo);
	}
}
