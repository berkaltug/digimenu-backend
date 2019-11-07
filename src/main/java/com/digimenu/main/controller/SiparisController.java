package com.digimenu.main.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digimenu.main.entity.Category;
import com.digimenu.main.entity.City;
import com.digimenu.main.entity.Menu;
import com.digimenu.main.entity.Restaurant;
import com.digimenu.main.service.CartService;
import com.digimenu.main.service.CityService;
import com.digimenu.main.service.MenuService;
import com.digimenu.main.service.RestaurantService;
import com.digimenu.main.service.Table_OrdersService;

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
	Collection<Menu> getMenu(@PathVariable("restaurantmenu") Long id) {
		// burada o şehre ait olmayan restoran idsi verince de menüyü gösteriyor göstermememsi lazım.
		//Qr koda gömücez ama gömerken de sıkıntı yaratabilir.
		return menuService.getMenuItemsByRestaurant(restaurantService.getRestaurant(id));
	}
	
	@PreAuthorize("hasRole('ADMIN') OR hasRole('RESTAURANT') OR hasRole('USER')")
	//Gerek var mı emin değilim
	@GetMapping("/{restaurantmenu}/{menuitem}") //tek bir itemi ismi ile çeker
	Menu getMenuItem(@PathVariable("restaurantmenu") Long id,@PathVariable("menuitem") String menuItem) {
		List<Menu> menu=new ArrayList<Menu>();
		menu=menuService.getMenuItemsByRestaurant(restaurantService.getRestaurant(id));
		List<Menu> result=new ArrayList<Menu>();
		try {
			result=menu.stream().filter(m->m.getItem().equals(menuItem)).collect(Collectors.toList());
			System.out.println(result);
			if (result.size()!=1) {
				throw new IllegalStateException(); //buraya alternatif bul!!!!!!!!!!!!!!!!!!!!!
			}
			else {
				return result.get(0);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return result.get(0);
	}	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("restaurant/{id}")
	Restaurant getRestaurant(@PathVariable("id") Long id) {
		return restaurantService.getRestaurant(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("restaurant/{id}/categories")
	Collection<Category> getCategories(@PathVariable("id") Long id){
		return restaurantService.getRestaurant(id).getCategories();
	}
}
