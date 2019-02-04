package com.digimenu.main.service;

import java.util.List;

import com.digimenu.main.entity.Table_Orders;

public interface Table_OrdersService {
	public Table_Orders getTable_Order(Long id);
	public List<Table_Orders> getByTableNo(Integer no);
	public List<Table_Orders> getTable_Orders();
	public void deleteTable_Order(Long id);
	public void addTable_Order(Table_Orders tableorders);

}
