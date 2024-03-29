package com.digimenu.main.domain.entity;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Menu{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	@NotEmpty(message = "Ürün ismi boş bırakılamaz")
	private String item;
	
	private String ingredients;
	private String options;

	@NotNull(message = "Ücret alanı boş bırakılamaz")
	@Digits(integer=6, fraction=2)
	private BigDecimal price;
	@JsonBackReference(value="restaurant-item")  //json ignore görevi gördü karşı bir @jsonMaagedRef olmamasına rağmen
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="restaurant_id")
	private Restaurant restaurant;

	private Float rating;
	private Integer voteCount;

	private String category;

	private Boolean isActive=true;

	private Boolean isFavourite=false;

	private String imagePublicId;


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

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
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

	public Boolean getFavourite() {
		return isFavourite;
	}

	public void setFavourite(Boolean favourite) {
		isFavourite = favourite;
	}

	public String getImagePublicId() {
		return imagePublicId;
	}

	public void setImagePublicId(String imagePublicId) {
		this.imagePublicId = imagePublicId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Menu menu = (Menu) o;
		return Objects.equals(id, menu.id) &&
				Objects.equals(item, menu.item) &&
				Objects.equals(ingredients, menu.ingredients) &&
				Objects.equals(options, menu.options) &&
				Objects.equals(price, menu.price) &&
				Objects.equals(restaurant, menu.restaurant) &&
				Objects.equals(rating, menu.rating) &&
				Objects.equals(voteCount, menu.voteCount) &&
				Objects.equals(category, menu.category) &&
				Objects.equals(isActive, menu.isActive) &&
				Objects.equals(isFavourite, menu.isFavourite) &&
				Objects.equals(imagePublicId, menu.imagePublicId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, item, ingredients, options, price, restaurant, rating, voteCount, category, isActive, isFavourite, imagePublicId);
	}

	@Override
	public String toString() {
		return "Menu{" +
				"id=" + id +
				", item='" + item + '\'' +
				", ingredients='" + ingredients + '\'' +
				", options='" + options + '\'' +
				", price=" + price +
				", restaurant=" + restaurant.getId() +
				", rating=" + rating +
				", voteCount=" + voteCount +
				", category='" + category + '\'' +
				", isActive=" + isActive +
				", isFavourite=" + isFavourite +
				", imagePublicId='" + imagePublicId + '\'' +
				'}';
	}
}

