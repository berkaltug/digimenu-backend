package com.digimenu.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.digimenu.main.dto.RestaurantDto;
import com.digimenu.main.entity.Cart;
import com.digimenu.main.entity.Menu;
import com.digimenu.main.entity.Restaurant;
import com.digimenu.main.service.CartService;
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
	@Autowired
	CartService cartService;
	
	
	@GetMapping("/login")
	public ModelAndView getlogin(Model model) {
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("login");
	    return mav;
	}
	
	@GetMapping("/additem")
	public String addItemGet(Menu menu,Model model) {
		
		String loggedInUser=securityService.findLoggedInUsername();
		model.addAttribute("category",
				restaurantService.getByOwner(userService.findByUsername(loggedInUser)).getCategories());
		return "addmenuitem";
	}
	
	@PostMapping("/additem")
	public String addItemPost(@ModelAttribute(value="menu") @Valid Menu menu,Model model,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "addmenuitem";
		}
		menu.setRestaurant(this.getRestaurant());
		this.menuService.saveMenuItem(menu);
		return "redirect:/restaurant/menu";
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
	
	@GetMapping("/edititem/{id}")
	public String editMenu(Model model,@PathVariable("id") Long id) {
		model.addAttribute("item",menuService.getMenuItem(id));
		return "edit";
	}
	
	@PutMapping("/edititem/{id}")
	public String editItem(@ModelAttribute("menu") @Valid Menu menu,Model model,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("error", bindingResult.getFieldError().toString());
			return "edit";
		}
		
		menuService.saveMenuItem(menu);
		return "redirect:/restaurant/menu";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteItem(@PathVariable("id") Long id) {
		try {
			menuService.deleteMenuItem(menuService.getMenuItem(id));
		}catch(Exception e) {
			System.err.println(e);
		}
		
		return "redirect:/restaurant/menu";
	}
	
	@GetMapping("/cart/{masa}")
	public String getCart(Model model,@PathVariable("masa") Integer id) {
		Restaurant res=getRestaurant();
		try {
			List<Cart> siparisler=cartService.getCart(res.getId(), id);
			
			model.addAttribute("orders",siparisler);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "showcart";
	}
	
	@GetMapping("/flushcart/{masa}")
	public String freeCart(@PathVariable("masa") Integer id) {
		Restaurant res=getRestaurant();
		try {
			cartService.emptyCart(res.getId(),id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/restaurant/tables";
	}
	
	private Restaurant getRestaurant() {
		String loggedInUser=securityService.findLoggedInUsername();
		Restaurant restaurant=restaurantService.getByOwner(userService.findByUsername(loggedInUser));
		return restaurant;
	}
	
}
