package com.digimenu.main.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class Menu {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	@NotNull
	private String item;
	
	private String ingredients;
	
	private Float price;
	@JsonBackReference(value="restaurant-item")  //json ignore görevi gördü karşı bir @jsonMaagedRef olmamasına rağmen
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="restaurant_id")
	private Restaurant restaurant;
	
	
	//fk bağlamadık o yüzden String , mappinge gerek yok diye düşündüm 
	private String category;

	private Boolean isActive=true;

	public Menu() {
	}

	public Menu(@NotNull String item, String ingredients, Float price, Restaurant restaurant, String category, Boolean isActive) {
		this.item = item;
		this.ingredients = ingredients;
		this.price = price;
		this.restaurant = restaurant;
		this.category = category;
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Menu)) return false;
		Menu menu = (Menu) o;
		return Objects.equals(id, menu.id) &&
				Objects.equals(item, menu.item) &&
				Objects.equals(ingredients, menu.ingredients) &&
				Objects.equals(price, menu.price) &&
				Objects.equals(restaurant, menu.restaurant) &&
				Objects.equals(category, menu.category) &&
				Objects.equals(isActive, menu.isActive);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, item, ingredients, price, restaurant, category, isActive);
	}

	@Override
	public String toString() {
		return "Menu{" +
				"id=" + id +
				", item='" + item + '\'' +
				", ingredients='" + ingredients + '\'' +
				", price=" + price +
				", restaurant=" + restaurant +
				", category='" + category + '\'' +
				", isActive=" + isActive +
				'}';
	}
}

