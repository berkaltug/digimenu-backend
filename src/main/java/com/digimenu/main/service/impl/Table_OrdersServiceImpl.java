package com.digimenu.main.service.impl;

import java.util.List;

import com.digimenu.main.service.Table_OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digimenu.main.domain.entity.Table_Orders;
import com.digimenu.main.repository.Table_OrdersRepository;
@Service
public class Table_OrdersServiceImpl implements Table_OrdersService {

	private Table_OrdersRepository tor;

	@Autowired
	public Table_OrdersServiceImpl(Table_OrdersRepository tor) {
		this.tor = tor;
	}

	@Override
	public Table_Orders getTable_Order(Long id) {
		return tor.getOne(id);
	}

	@Override
	public List<Table_Orders> getByTableNo(Integer no) {
		return tor.getByMasaNo(no);
	}

	@Override
	public List<Table_Orders> getTable_Orders() {
		return tor.findAll();
	}

	@Override
	public void deleteTable_Order(Long id) {
		tor.delete(tor.getOne(id));
	}

	@Override
	public void addTable_Order(Table_Orders tableorders) {
		tor.save(tableorders);
	}


	@Override
	public void insertOrder(Long id,Long res, Integer masa) {
		tor.insertTable_Order(id.intValue(),res, masa);
	}

}
