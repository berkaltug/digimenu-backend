package com.digimenu.main.service;

import java.util.List;
import java.util.Optional;

import com.digimenu.main.domain.entity.Menu;
import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.domain.response.GetMenuResponse;

public interface MenuService {
	GetMenuResponse getMenuItemsByRestaurant(Long id);
	List<Menu> getAllItemsByRestaurant(Long id);
	List<Menu> getPassiveItemsByRestaurant(Long id);
	Menu getMenuItem(Long id);
	void saveMenuItem(Menu menu);
	void deleteMenuItem(Menu menu);
	void updateMenuItem(Menu menu);
	List<Menu> getFavoriteItemsByRestaurant(Long id);
	Optional<Menu> getMenuByRestaurantAndName(Restaurant restaurant, String name);
}
