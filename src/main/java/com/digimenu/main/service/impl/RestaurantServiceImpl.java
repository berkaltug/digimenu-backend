package com.digimenu.main.service.impl;

import com.digimenu.main.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digimenu.main.entity.Restaurant;
import com.digimenu.main.repository.RestaurantRepository;
import com.digimenu.main.security.User;
@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	private RestaurantRepository rr;
	@Autowired
	public RestaurantServiceImpl(RestaurantRepository rr) {
		this.rr = rr;
	}

	@Override
	public Restaurant getRestaurant(Long id) {
		return rr.getOne(id);
	}
	@Override
	public Restaurant getByOwner(User owner) {
		return rr.findByOwner(owner);
	}

}
