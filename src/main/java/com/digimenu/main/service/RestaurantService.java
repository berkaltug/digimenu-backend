package com.digimenu.main.service;

import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.security.User;

public interface RestaurantService {
	Restaurant getRestaurant(Long id);
	Restaurant getByOwner(User owner);
	Restaurant getLoggedInRestaurant();
	String getLoggedInRestaurantUsername();
}
