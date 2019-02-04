package com.digimenu.main.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@OneToOne
	@JoinColumn(name="table_orders_id")
	private Table_Orders table_orders;
	
	private Integer totalPrice;

	public Cart(Long id, Table_Orders table_orders, Integer totalPrice) {
		this.id = id;
		this.table_orders = table_orders;
		this.totalPrice = totalPrice;
	}

	public Cart() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Table_Orders getTable_orders() {
		return table_orders;
	}

	public void setTable_orders(Table_Orders table_orders) {
		this.table_orders = table_orders;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
	
}
