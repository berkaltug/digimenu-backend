package com.digimenu.main.service;

import java.util.Date;
import java.util.List;

import com.digimenu.main.domain.dto.TableOrderDto;
import com.digimenu.main.domain.entity.Table_Orders;
import com.digimenu.main.domain.response.CreateOrderResponse;
import com.digimenu.main.domain.response.ReportResponse;

public interface Table_OrdersService {
	Table_Orders getTable_Order(Long id);
	List<Table_Orders> getByTableNo(Integer no);
	List<Table_Orders> getTable_Orders();
	void deleteTable_Order(Long id);
	void addTable_Order(Table_Orders tableorders);
	CreateOrderResponse createOrder(TableOrderDto tableOrderDto);
	ReportResponse getReport(Date startdate, Date endate);

}
