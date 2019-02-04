package com.digimenu.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digimenu.main.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
