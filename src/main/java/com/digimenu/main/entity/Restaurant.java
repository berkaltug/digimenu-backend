package com.digimenu.main.entity;

import java.util.List;
import java.util.ArrayList;
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
import javax.validation.constraints.NotNull;

import com.digimenu.main.JsonSerializer.CustomJsonDeserializer;
import com.digimenu.main.JsonSerializer.CustomJsonSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
	@NotNull
	private String owner;
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


//	@NotNull
//	private RestaurantAcc restaurantAcc;
	public Restaurant(Long id, @NotNull String name, City city, @NotNull String address, @NotNull Long tel,
			@NotNull String mail, @NotNull String owner, 
//			@NotNull List<Menu> menu,
//			List<Table_Orders> orders,
			List<Category> categories) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.address = address;
		this.tel = tel;
		this.mail = mail;
		this.owner = owner;
//		this.menu = menu;
//		this.orders=orders;
		this.categories=categories;
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
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
//	public List<Menu> getMenu() {
//		return menu;
//	}
//	public void setMenu(List<Menu> menu) {
//		this.menu = menu;
//	}

//	public List<Table_Orders> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Table_Orders> orders) {
//		this.orders = orders;
//	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", name=" + name + ", city=" + city.toString() + ", address=" + address + ", tel=" + tel
				+ ", mail=" + mail + ", owner=" + owner + ", categories=" + categories + "]";
	}
	
	
	

	
	
}
