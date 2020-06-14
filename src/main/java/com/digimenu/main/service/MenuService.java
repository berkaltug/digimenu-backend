package com.digimenu.main.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.digimenu.main.domain.dto.PanelMenuDto;
import com.digimenu.main.domain.entity.Menu;
import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.domain.response.GetMenuResponse;
import com.digimenu.main.domain.response.MenuResponseItem;

public interface MenuService {
	GetMenuResponse getMenuItemsByRestaurant(Long id);
	List<Menu> getAllItemsByRestaurant(Long id);
	List<Menu> getPassiveItemsByRestaurant(Long id);
	Menu getMenuItem(Long id);
	void saveMenuItem(PanelMenuDto panelMenuDto);
	void deleteMenuItem(Menu menu);
	void updateMenuItem(PanelMenuDto panelMenuDto);
	void updateMenuItem(Menu menu);
	List<Menu> getFavoriteItemsByRestaurant(Long id);
	Optional<Menu> getMenuByRestaurantAndName(Restaurant restaurant, String name);
	Map<String,List<MenuResponseItem>> orderItemsByCategory(List<MenuResponseItem> items);
}
