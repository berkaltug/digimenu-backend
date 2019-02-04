package com.digimenu.main.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class City {
	
	@Id
	@NotNull
	private Integer id;
	@NotNull
	private String name;
	@JsonManagedReference
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="city")
	private List<Restaurant> restaurants=new ArrayList<Restaurant>();
	
	public City(Integer id, String name, List<Restaurant> restaurants) {
		this.id = id;
		this.name = name;
		this.restaurants = restaurants;
	}

	public City() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	

	
}
