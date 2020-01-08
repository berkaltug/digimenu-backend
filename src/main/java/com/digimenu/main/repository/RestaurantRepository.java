package com.digimenu.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.security.User;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	Restaurant findByOwner(User owner);
}
