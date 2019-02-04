package com.digimenu.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digimenu.main.entity.Menu;
import com.digimenu.main.entity.Restaurant;
import com.digimenu.main.repository.MenuRepository;
@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuRepository mr;
	
	@Override
	public List<Menu> getMenuItemsByRestaurant(Restaurant res) {
		return mr.getByRestaurant(res.getId());
	}
	



	
}
