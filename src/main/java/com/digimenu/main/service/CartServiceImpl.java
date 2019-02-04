package com.digimenu.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digimenu.main.entity.Cart;
import com.digimenu.main.repository.CartRepository;
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepository cr;
	@Override
	public Cart getCart(Long id) {
		return cr.getOne(id); 
	}

	@Override
	public void deleteCart(Long id) {
		cr.delete(cr.getOne(id));
	}

}
