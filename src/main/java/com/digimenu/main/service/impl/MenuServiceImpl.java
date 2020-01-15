package com.digimenu.main.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.digimenu.main.domain.response.GetMenuResponse;
import com.digimenu.main.service.MenuService;
import com.digimenu.main.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digimenu.main.domain.entity.Menu;
import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.repository.MenuRepository;
@Service
public class MenuServiceImpl implements MenuService {
	
	private MenuRepository mr;
	private RestaurantService rs;

	@Autowired
	public MenuServiceImpl(MenuRepository mr, RestaurantService rs) {
		this.mr = mr;
		this.rs = rs;
	}

	@Override
	public GetMenuResponse getMenuItemsByRestaurant(Long id) {
		GetMenuResponse response = new GetMenuResponse();
		response.setItems(
				mr.getByRestaurant(id)
				.stream()
				.filter(item -> item.getActive())
				.collect(Collectors.toList())
		);
		return response;
	}

	@Override
	public Menu getMenuItem(Long id) {
		return mr.getOne(id);
	}

	@Override
	public void saveMenuItem(Menu menu) {
		mr.save(menu);
	}

	@Override
	public void deleteMenuItem(Menu menu) {
		mr.delete(menu);
	}
	
	@Override
	@Transactional
	@Modifying
	public void updateMenuItem(Menu menu) {
		mr.save(menu);
	}
	



	
}
