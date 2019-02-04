package com.digimenu.main.service;

import java.util.List;

import com.digimenu.main.entity.Menu;
import com.digimenu.main.entity.Restaurant;

public interface MenuService {
	public List<Menu> getMenuItemsByRestaurant(Restaurant res);
}
