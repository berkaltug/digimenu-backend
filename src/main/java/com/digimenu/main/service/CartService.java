package com.digimenu.main.service;

import java.util.List;

import com.digimenu.main.entity.Cart;

public interface CartService {
	
	public  List<Cart> getCart(Long id,Integer no);
	
	public void deleteCart(Long id);
	
	public Cart addCart(Cart c);
	
	public void emptyCart(Long id , Integer no);
}
