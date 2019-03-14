package com.digimenu.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.digimenu.main.dto.RestaurantDto;
import com.digimenu.main.entity.Restaurant;
import com.digimenu.main.service.CustomUserDetailsService;
import com.digimenu.main.service.MenuService;
import com.digimenu.main.service.RestaurantService;
import com.digimenu.main.service.SecurityService;
import com.digimenu.main.service.UserService;

@Controller
@RequestMapping(value="/restaurant")
public class RestaurantController {
	
	@Autowired 
	SecurityService securityService;
	@Autowired
	UserService userService;
	@Autowired
	RestaurantService restaurantService;
	@Autowired
	MenuService menuService;
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView getlogin(Model model) {
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("login");
	    return mav;
	}
	
	@GetMapping("/additem")
	public String addItem(Model model) {
		String loggedInUser=securityService.findLoggedInUsername();
		model.addAttribute("category",
				restaurantService.getByOwner(userService.findByUsername(loggedInUser))
				.getCategories());
		return "addmenuitem";
	}
	
	@GetMapping("/tables")
	public String getTables(Model model) {
		Restaurant restaurant=getRestaurant();
		model.addAttribute("tables",restaurant.getTableAmount());
		return "showtable";
	}
	
	@GetMapping("/menu")
	public String getMenu(Model model) {
		Restaurant restaurant=getRestaurant();
		model.addAttribute("menu",menuService.getMenuItemsByRestaurant(restaurant));
		return "showmenu";
	}
	
	private Restaurant getRestaurant() {
		String loggedInUser=securityService.findLoggedInUsername();
		Restaurant restaurant=restaurantService.getByOwner(userService.findByUsername(loggedInUser));
		return restaurant;
	}
	
}
