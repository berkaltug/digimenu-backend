package com.digimenu.main.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.digimenu.main.entity.Table_Orders;

public interface Table_OrdersRepository extends JpaRepository<Table_Orders, Long> {
	
	@Query("SELECT t FROM Table_Orders t WHERE t.masa = :masa_no")
	List<Table_Orders> getByMasaNo(@Param("masa_no")Integer no);
	
	@Transactional
	@Query(value="insert into Table_Orders (id,masa,restaurant_id) values (:o_id,:masa_id , (select id from Restaurant r where r.id=:id))",nativeQuery=true)   
	void insertTable_Order(@Param("o_id")Integer id,@Param("id") Long res,@Param("masa_id")Integer masa );
}
