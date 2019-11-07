package com.digimenu.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digimenu.main.entity.City;
import com.digimenu.main.service.CityService;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/city")
public class CityController {
	

	private CityService cityService;
	@Autowired
	public CityController(CityService cityService) {
		this.cityService = cityService;
	}

	@GetMapping("/{il}")
	public City getCity(@PathVariable("il") Integer id) {
		return cityService.getCity(id);
	}

}
