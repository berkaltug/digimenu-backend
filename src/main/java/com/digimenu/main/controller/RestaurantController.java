package com.digimenu.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/restaurant")
public class RestaurantController {

	@GetMapping("/login")
	public String getlogin() {
		return "login";
	}
	
	@GetMapping("/login-error")
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
