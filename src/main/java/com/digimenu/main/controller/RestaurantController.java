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
import com.digimenu.main.service.CustomUserDetailsService;

@Controller
@RequestMapping(value="/restaurant")
public class RestaurantController {
	
	@Autowired 
	CustomUserDetailsService userDetailsService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView getlogin(Model model) {
		ModelAndView mav = new ModelAndView();
	    mav.setViewName("login");
	    return mav;
	}
//	@RequestMapping(value="/login" ,method=RequestMethod.POST)
//	public String postlogin(@ModelAttribute("restaurantDto") RestaurantDto resDto,Model model) {
//		
//		Authentication authentication=SecurityContextHolder.getContext().getAuthentication(); //kullanmadık
//		UserDetails userDetails=null;
//		
//		
//		if( userDetails!=null ) {
//			return "restaurant/showtable";
//		}
//	}
	@GetMapping("/login----")
	public String errorlogin(Model model) {
		 model.addAttribute("loginError", true);
		return "login";
	}
	
	@GetMapping("/tables")
	public String getTables() {
		return "showtable";
	}
	
	@GetMapping("/menu")
	public String getMenu() {
		return "showmenu";
	}
	
}
