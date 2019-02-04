package com.digimenu.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digimenu.main.entity.City;

public interface CityRepository extends JpaRepository<City, Integer> {

}
