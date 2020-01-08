package com.digimenu.main.service.impl;

import java.util.List;
import java.util.Optional;

import com.digimenu.main.domain.dto.TransferCartDto;
import com.digimenu.main.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digimenu.main.domain.entity.Cart;
import com.digimenu.main.repository.CartRepository;

import javax.transaction.Transactional;

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

	@Override
	@Transactional
	public Optional<List<Cart>> transferCart(TransferCartDto dto) {
		List<Cart> data = cr.getCart(dto.getId(), dto.getSource());
		if (data.isEmpty() || data == null) {
			return Optional.empty();
		} else {
			data.forEach(item -> item.setMasaNo(dto.getTarget()));
			List<Cart> result = cr.saveAll(data);
			if (result.isEmpty() || result == null) {
				return Optional.empty();
			} else {
				cr.flushCart(dto.getId(), dto.getSource());
				return Optional.of(result);
			}
		}
	}

}
