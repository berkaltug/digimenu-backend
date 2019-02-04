package com.digimenu.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digimenu.main.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
