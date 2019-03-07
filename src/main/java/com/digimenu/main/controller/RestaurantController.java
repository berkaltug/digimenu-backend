package com.digimenu.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/restaurant")
public class RestaurantController {

	@GetMapping("/login")
	public String getlogin() {
		return "login";
	}
}
