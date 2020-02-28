package com.digimenu.main.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.digimenu.main.domain.response.GetMenuResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digimenu.main.domain.entity.Category;
import com.digimenu.main.domain.entity.Menu;
import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.service.MenuService;
import com.digimenu.main.service.RestaurantService;

@RestController
@RequestMapping("/menu")
public class SiparisController {
	
	private MenuService menuService;
	private RestaurantService restaurantService;

	@Autowired
	public SiparisController(MenuService menuService, RestaurantService restaurantService) {
		this.menuService = menuService;
		this.restaurantService = restaurantService;
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	//@Secured("USER")
	@GetMapping("/{restaurantmenu}") //bütün menüyü çeker
	GetMenuResponse getMenu(@PathVariable("restaurantmenu") Long id) {
		return menuService.getMenuItemsByRestaurant(id);
	}
}
