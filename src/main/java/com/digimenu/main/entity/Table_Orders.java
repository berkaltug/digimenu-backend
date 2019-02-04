package com.digimenu.main.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity

public class Table_Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	//@JsonIgnoreProperties("restaurant")
	//@JsonManagedReference
	//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
	//@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="orders")
	private Restaurant restaurant;
	
	
	@NotNull
	private Integer masa;
	
	@OneToOne(cascade=CascadeType.ALL,mappedBy="table_orders")
	private Cart cart;
	
	

	public Table_Orders(Long id,Restaurant restaurant, Integer masa, Cart cart) {
		this.id = id;
		this.restaurant=restaurant;
		this.masa = masa;
		this.cart = cart;
	}
	

	public Table_Orders() {
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
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
	
	
	
	
}
