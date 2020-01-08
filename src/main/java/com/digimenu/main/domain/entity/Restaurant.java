package com.digimenu.main.domain.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.digimenu.main.security.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Restaurant {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	
	private String name;
	@JsonBackReference(value="city-restaurant")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="city_id",nullable=false)
	private City city;
	@NotNull
	private String address;
	@NotNull
	private Long tel;
	@NotNull
	private String mail;
	@OneToOne
	@JoinColumn(name="owner_id",nullable=false)
	private User owner;
//	@NotNull
//	@JsonManagedReference(value="restaurant-item")
//	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="restaurant")
//	private List<Menu> menu;
//	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="restaurant")
//	private List<Table_Orders> orders;
//	@JsonManagedReference(value="restaurant-category")
	@ManyToMany
	@JoinTable(name="restaurant_category",
	joinColumns= {@JoinColumn(name="restaurant_id")},
	inverseJoinColumns= {@JoinColumn(name="category_id")})
	private List<Category> categories;
	
	@NotNull
	private Integer tableAmount;
	
public Restaurant(Long id, @NotNull String name, City city, @NotNull String address, @NotNull Long tel,
		@NotNull String mail, User owner, List<Category> categories,Integer tableAmount) {
	this.id = id;
	this.name = name;
	this.city = city;
	this.address = address;
	this.tel = tel;
	this.mail = mail;
	this.owner = owner;
	this.categories = categories;
	this.tableAmount=tableAmount;
}


public Restaurant() {
}


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public City getCity() {
	return city;
}


public void setCity(City city) {
	this.city = city;
}


public String getAddress() {
	return address;
}


public void setAddress(String address) {
	this.address = address;
}


public Long getTel() {
	return tel;
}


public void setTel(Long tel) {
	this.tel = tel;
}


public String getMail() {
	return mail;
}


public void setMail(String mail) {
	this.mail = mail;
}


public User getOwner() {
	return owner;
}


public void setOwner(User owner) {
	this.owner = owner;
}


public List<Category> getCategories() {
	return categories;
}


public void setCategories(List<Category> categories) {
	this.categories = categories;
}


public Integer getTableAmount() {
	return tableAmount;
}


public void setTableAmount(Integer tableAmount) {
	this.tableAmount = tableAmount;
}


	
}
