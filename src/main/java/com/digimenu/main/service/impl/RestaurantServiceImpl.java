package com.digimenu.main.service.impl;

import com.digimenu.main.service.RestaurantService;
import com.digimenu.main.service.SecurityService;
import com.digimenu.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.repository.RestaurantRepository;
import com.digimenu.main.security.User;
@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	private RestaurantRepository rr;
	private SecurityService securityService;
	private UserService userService;

	@Autowired
	public RestaurantServiceImpl(RestaurantRepository rr, SecurityService securityService, UserService userService) {
		this.rr = rr;
		this.securityService = securityService;
		this.userService = userService;
	}

	@Override
	public Restaurant getRestaurant(Long id) {
		return rr.getOne(id);
	}
	@Override
	public Restaurant getByOwner(User owner) {
		return rr.findByOwner(owner);
	}

	@Override
	public Restaurant getLoggedInRestaurant() {
		String loggedInUser=securityService.findLoggedInUsername();
		Restaurant restaurant=rr.findByOwner(userService.findByUsername(loggedInUser));
		return restaurant;
	}

	@Override
	public String getLoggedInRestaurantUsername() {
		return securityService.findLoggedInUsername();
	}
	
}
