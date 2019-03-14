package com.digimenu.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.digimenu.main.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
	
	// 2 sorgu da aynı çıktıyı veriyor
	@Query("SELECT m FROM Menu m WHERE m.restaurant IN (SELECT r.id FROM Restaurant r WHERE r.id=:res) ") // burada restaurandan id çekip öyle query yazmayı dene
	//@Query(value="select * from public.menu m where m.restaurant_id= :res",nativeQuery=true)
	List<Menu> getByRestaurant(@Param("res")Long res);
}
