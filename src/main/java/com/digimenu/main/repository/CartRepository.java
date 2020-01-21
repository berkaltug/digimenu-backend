package com.digimenu.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.digimenu.main.domain.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	@Query("SELECT c FROM Cart c WHERE c.restaurantId = :id AND c.masaNo = :no ")
	List<Cart> getCart(@Param("id")Long id,@Param("no")Integer no);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Cart c WHERE c.restaurantId = :id AND c.masaNo = :no ")
	void flushCart(@Param("id")Long id,@Param("no")Integer no);

	@Transactional
	@Modifying
	@Query("UPDATE Cart c SET c.isDelivered = :value WHERE c.id=:id")
	int updateDeliveryField(@Param("value") Boolean value,@Param("id") Long id);

}
