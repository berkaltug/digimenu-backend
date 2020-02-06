package com.digimenu.main.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.digimenu.main.domain.dto.CallWaitressDto;
import com.digimenu.main.domain.dto.TableOrderDto;
import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.domain.entity.Table_Orders;
import com.digimenu.main.domain.response.CallWaitressResponse;
import com.digimenu.main.domain.response.CreateOrderResponse;
import com.digimenu.main.domain.response.ReportResponse;

public interface Table_OrdersService {
	Table_Orders getTable_Order(Long id);
	List<Table_Orders> getByTableNo(Integer no);
	List<Table_Orders> getTable_Orders();
	void deleteTable_Order(Long id);
	void addTable_Order(Table_Orders tableorders);
	Optional<CreateOrderResponse> createOrder(TableOrderDto tableOrderDto);
	ReportResponse getReport(Timestamp startdate, Timestamp endate);
	void deleteWrongTableOrder(Restaurant res,String name,Integer masaNo);
	Optional<CallWaitressResponse> callWaitress(CallWaitressDto dto);
}
