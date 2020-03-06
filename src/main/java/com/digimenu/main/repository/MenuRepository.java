package com.digimenu.main.repository;

import java.util.List;

import com.digimenu.main.domain.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.digimenu.main.domain.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
	
	@Query(value="select * from public.menu m where m.restaurant_id= :res",nativeQuery=true)
	List<Menu> getByRestaurant(@Param("res")Long res);
}
