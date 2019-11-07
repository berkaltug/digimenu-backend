package com.digimenu.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
	
	SecurityService securityService;
	UserService userService;
	RestaurantService restaurantService;
	MenuService menuService;
	CartService cartService;
	@Autowired
	public RestaurantController(SecurityService securityService, UserService userService, RestaurantService restaurantService, MenuService menuService, CartService cartService) {
		this.securityService = securityService;
		this.userService = userService;
		this.restaurantService = restaurantService;
		this.menuService = menuService;
		this.cartService = cartService;
	}

	@GetMapping("/login")
	public ModelAndView getlogin(Model model) {
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("login");
	    return mav;
	}
	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@GetMapping("/additem")
	public String addItemGet(Menu menu,Model model) {
		
		String loggedInUser=securityService.findLoggedInUsername();
		model.addAttribute("category",
				restaurantService.getByOwner(userService.findByUsername(loggedInUser)).getCategories());
		return "addmenuitem";
	}
	
	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@PostMapping("/additem")
	public String addItemPost(@ModelAttribute(value="menu") @Valid Menu menu,Model model,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "addmenuitem";
		}
		menu.setRestaurant(this.getRestaurant());
		this.menuService.saveMenuItem(menu);
		return "redirect:/restaurant/menu";
	}
	
	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@GetMapping("/tables")
	public String getTables(Model model) {
		Restaurant restaurant=getRestaurant();
		model.addAttribute("tables",restaurant.getTableAmount());
		return "showtable";
	}
	
	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@GetMapping("/menu")
	public String getMenu(Model model) {
		Restaurant restaurant=getRestaurant();
		model.addAttribute("menu",menuService.getMenuItemsByRestaurant(restaurant));
		return "showmenu";
	}
	
	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@GetMapping("/edititem/{id}")
	public String editMenu(Model model,@PathVariable("id") Long id) {
		Restaurant res=getRestaurant();
		model.addAttribute("category",res.getCategories());
		model.addAttribute("menu",menuService.getMenuItem(id));
		return "editmenuitem";
	}
	
	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@PostMapping("/updateitem")
	public String editItem(@ModelAttribute(value="menu") @Valid Menu menu) {
		
		menuService.updateMenuItem(menu);
		return "redirect:/restaurant/menu";
	}

	@ResponseBody
	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@GetMapping("/delete/{id}")
	public String deleteItem(@PathVariable("id") Long id) {
		try {
			menuService.deleteMenuItem(menuService.getMenuItem(id));
		}catch(Exception e) {
			System.err.println(e);
		}
		
		return "redirect:/restaurant/menu";
	}
	
	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
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
	
	@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
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

	@CrossOrigin
	@ResponseBody
	//@PreAuthorize("hasRole('RESTAURANT') OR hasRole('ADMIN')")
	@GetMapping("/flushitem/{id}")
	public ResponseEntity<String> freeCartItem(@PathVariable("id") Long id) {
		try {
			cartService.deleteCart(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//login olmuş restoranı çeker
	private Restaurant getRestaurant() {
		String loggedInUser=securityService.findLoggedInUsername();
		Restaurant restaurant=restaurantService.getByOwner(userService.findByUsername(loggedInUser));
		return restaurant;
	}
	
}
