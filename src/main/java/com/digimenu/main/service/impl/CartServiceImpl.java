package com.digimenu.main.service.impl;

import java.util.List;

import com.digimenu.main.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digimenu.main.entity.Cart;
import com.digimenu.main.repository.CartRepository;
@Service
public class CartServiceImpl implements CartService {

	private CartRepository cr;

	@Autowired
	public CartServiceImpl(CartRepository cr) {
		this.cr = cr;
	}

	@Override
	public List<Cart> getCart(Long id,Integer no) {
		return cr.getCart(id, no);
	}

	@Override
	public void deleteCart(Long id) { //kullanılmıyor test amaçlı yazdım
		cr.delete(cr.getOne(id));
	}

	@Override
	public Cart addCart(Cart c) { //kullanılmıyor test amaçlı yazdım
		return cr.save(c);
	}

	@Override
	public void emptyCart(Long id, Integer no) {
		cr.flushCart(id, no);
	}

}
