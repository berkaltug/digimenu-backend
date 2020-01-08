package com.digimenu.main.service;

import java.util.List;

import com.digimenu.main.domain.entity.Menu;
import com.digimenu.main.domain.entity.Restaurant;

public interface MenuService {
	public List<Menu> getMenuItemsByRestaurant(Restaurant res);
	public Menu getMenuItem(Long id);
	public void saveMenuItem(Menu menu);
	public void deleteMenuItem(Menu menu);
	public void updateMenuItem(Menu menu);
}
