package com.digimenu.main.service;

import com.digimenu.main.entity.Cart;

public interface CartService {
	
	public  Cart getCart(Long id);
	
	public void deleteCart(Long id);
}
