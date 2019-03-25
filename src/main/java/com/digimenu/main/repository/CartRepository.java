package com.digimenu.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.digimenu.main.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	@Query("SELECT c FROM Cart c WHERE c.restaurantId = :id AND c.masaNo = :no ")
	List<Cart> getCart(@Param("id")Long id,@Param("no")Integer no);
	@Query("DELETE FROM Cart c WHERE c.restaurantId = :id AND c.masaNo = :no ")
	void flushCart(@Param("id")Long id,@Param("no")Integer no);
}
