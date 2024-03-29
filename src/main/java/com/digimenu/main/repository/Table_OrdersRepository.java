package com.digimenu.main.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Date;

import com.digimenu.main.domain.dto.ReportDto;
import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.domain.projection.PastOrdersProjection;
import com.digimenu.main.domain.projection.ReportProjection;
import com.digimenu.main.security.User;
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
	@Query(value = "DELETE FROM public.table_orders t WHERE t.id IN (SELECT t.id FROM public.table_orders t WHERE t.restaurant_id=?1  AND t.item=?2 AND t.masa=?3 LIMIT 1)", nativeQuery=true)
	int deleteWrongOrder( long resId , String name , Integer masaNo);


	//çalışmıyor bu halini düzeltip kullanmak lazım
//	@Query(value = "SELECT count(t.item) AS count,t.item AS name,t.restaurant AS restaurantId, " + "DAY(t.siparisTarihi) AS orderDate " +
//			"FROM Table_Orders t " +
//			"WHERE t.user = :userId " +
//			"GROUP BY t.item , t.restaurant,DAY(t.siparisTarihi)")
//	List<PastOrdersProjection> getPastUserOrders(@Param("userId") User user);

	@Query(value = "select count(t.item) as count ,sum(t.price) as total,t.item as name , t.restaurant_id as restaurantId , (select r.name from restaurant r where r.id=t.restaurant_id) as restaurantName, date(t.siparis_tarihi) as orderDate " +
			"from table_orders t " +
			"where t.user_id = :userId " +
			"group by t.item,t.restaurant_id,date(t.siparis_tarihi)" , nativeQuery = true)
	List<PastOrdersProjection> getPastUserOrders(@Param("userId") Long userId );
}
