package com.digimenu.main.service;

import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.security.User;

public interface RestaurantService {
	public Restaurant getRestaurant(Long id);
	public Restaurant getByOwner(User owner);
}
