package com.digimenu.main.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="item")
	private Menu item;
	
	private Float price;
	



public Table_Orders(Long id, Restaurant restaurant, @NotNull Integer masa, Menu item, Float price) {
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

public Menu getItem() {
	return item;
}

public void setItem(Menu item) {
	this.item = item;
}



public Float getPrice() {
	return price;
}

public void setPrice(Float price) {
	this.price = price;
}

@Override
public String toString() {
	return "Table_Orders [id=" + id + ", restaurant=" + restaurant + ", masa=" + masa + ", item=" + item + ", price="
			+ price + "]";
	}
}
	
	
	
	

