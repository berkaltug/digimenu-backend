package com.digimenu.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digimenu.main.domain.entity.City;

public interface CityRepository extends JpaRepository<City, Integer> {

}
