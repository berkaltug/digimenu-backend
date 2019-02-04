package com.digimenu.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digimenu.main.entity.Restaurant;
import com.digimenu.main.repository.RestaurantRepository;
@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	private RestaurantRepository rr;
	@Override
	public Restaurant getRestaurant(Long id) {
		return rr.getOne(id);
	}

}
