package com.digimenu.main.service;

import java.util.List;
import java.util.Optional;

import com.digimenu.main.domain.dto.TransferCartDto;
import com.digimenu.main.domain.entity.Cart;

public interface CartService {
	
	  List<Cart> getCart(Long id,Integer no);
	  void deleteCart(Long id);
	  Cart addCart(Cart c);
	  void emptyCart(Long id , Integer no);
	  Optional<List<Cart>> transferCart(TransferCartDto dto);
	  List<Cart> saveAllCart(List<Cart> carts);
}
