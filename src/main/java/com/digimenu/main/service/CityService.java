package com.digimenu.main.service;

import java.util.List;

import com.digimenu.main.entity.City;

public interface CityService {
	
	public City getCity(Integer id);
	
	public void deleteCity(Integer id);
	
	public List<City> getAllCities();
}
