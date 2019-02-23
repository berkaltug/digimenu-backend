package com.digimenu.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digimenu.main.entity.Cart;
import com.digimenu.main.repository.CartRepository;
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private CartRepository cr;
	@Override
	public List<Cart> getCart(Long id,Integer no) {
		return cr.getCart(id, no);
	}

	@Override
	public void deleteCart(Long id) {
		cr.delete(cr.getOne(id));
	}

	@Override
	public Cart addCart(Cart c) { //kullanılmıyor
		return cr.save(c);
	}

}
