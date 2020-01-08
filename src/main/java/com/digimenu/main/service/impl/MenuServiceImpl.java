package com.digimenu.main.service.impl;

import java.util.List;

import com.digimenu.main.service.MenuService;
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
	@Autowired
	public MenuServiceImpl(MenuRepository mr) {
		this.mr = mr;
	}

	@Override
	public List<Menu> getMenuItemsByRestaurant(Restaurant res) {
		return mr.getByRestaurant(res.getId());
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
