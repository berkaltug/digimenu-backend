package com.digimenu.main.service;

import java.util.List;

import com.digimenu.main.domain.entity.Menu;
import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.domain.response.GetMenuResponse;

public interface MenuService {
	GetMenuResponse getMenuItemsByRestaurant(Long id);
	GetMenuResponse getAllItemsByRestaurant(Long id);
	GetMenuResponse getPassiveItemsByRestaurant(Long id);
	Menu getMenuItem(Long id);
	void saveMenuItem(Menu menu);
	void deleteMenuItem(Menu menu);
	void updateMenuItem(Menu menu);

}
