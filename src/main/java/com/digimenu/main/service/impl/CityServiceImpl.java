package com.digimenu.main.service.impl;

import java.util.List;

import com.digimenu.main.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digimenu.main.entity.City;
import com.digimenu.main.repository.CityRepository;
@Service
public class CityServiceImpl implements CityService {
	
	@Autowired 
	private CityRepository cr;
	@Override
	public City getCity(Integer id) {
		return cr.getOne(id);
	}

	@Override
	public void deleteCity(Integer id) {
		cr.delete(cr.getOne(id));
	}

	@Override
	public List<City> getAllCities() {
		return cr.findAll();
	}

}
