package com.digimenu.main.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Date;

import com.digimenu.main.domain.dto.ReportDto;
import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.domain.projection.ReportProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;

import com.digimenu.main.domain.entity.Table_Orders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TemporalType;

public interface Table_OrdersRepository extends JpaRepository<Table_Orders, Long> {
	
	@Query("SELECT t FROM Table_Orders t WHERE t.masa = :masa_no")
	List<Table_Orders> getByMasaNo(@Param("masa_no")Integer no);


	@Query("SELECT COUNT(t.item) AS count,t.item AS name,SUM(t.price) AS totalPrice " +
			"FROM Table_Orders t " +
			"WHERE t.restaurant = :restaurant_id AND t.siparisTarihi BETWEEN :startdate AND :enddate " +
			"GROUP BY t.item")
	List<ReportProjection> getSellReport(@Param("restaurant_id") Restaurant res,
										 @Param("startdate") Timestamp startdate,
										 @Param("enddate") Timestamp enddate);

	@Transactional
	@Modifying
	@Query(
			"DELETE FROM Table_Orders t WHERE t IN (SELECT DISTINCT t FROM Table_Orders t WHERE t.restaurant = :restaurant_id AND t.item = :item_name AND t.masa = :masa_no)"
	)
	int deleteWrongOrder( @Param("restaurant_id") Restaurant res , @Param("item_name")String name , @Param("masa_no") Integer masaNo);
}
