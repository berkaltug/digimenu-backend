package com.digimenu.main.domain.entity;


import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
//@JsonIdentityInfo(
//		  generator = ObjectIdGenerators.PropertyGenerator.class, 
//		  property = "id")
public class Table_Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="restaurant_id")
	private Restaurant restaurant;
	
	
	@NotNull
	private Integer masa;
	
//	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//	@JoinColumn(name="item")
//	private Menu item;
	private String item;
	private BigDecimal price;
	



public Table_Orders(Long id, Restaurant restaurant, @NotNull Integer masa, String item, BigDecimal price) {
		this.id = id;
		this.restaurant = restaurant;
		this.masa = masa;
		this.item = item;
		this.price = price;
	}

public Table_Orders() {
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Restaurant getRestaurant() {
	return restaurant;
}

public void setRestaurant(Restaurant restaurant) {
	this.restaurant = restaurant;
}

public Integer getMasa() {
	return masa;
}

public void setMasa(Integer masa) {
	this.masa = masa;
}

public String getItem() {
	return item;
}

public void setItem(String item) {
	this.item = item;
}



public BigDecimal getPrice() {
	return price;
}

public void setPrice(BigDecimal price) {
	this.price = price;
}

@Override
public String toString() {
	return "Table_Orders [id=" + id + ", restaurant=" + restaurant + ", masa=" + masa + ", item=" + item + ", price="
			+ price + "]";
	}
}
	
	
	
	

