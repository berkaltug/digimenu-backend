package com.digimenu.main.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class City { // deftere yazdığın gibi addRestaurant metodu ekle !!!
	
	@Id
	@NotNull
	private Integer id;
	@NotNull
	private String name;
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

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", restaurants=" + restaurants + "]";
	}
	
	
	

	
}
