package com.digimenu.main.domain.entity;


import com.digimenu.main.security.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
public class Table_Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="restaurant_id")
	private Restaurant restaurant;
	
	
	@NotNull
	private Integer masa;

	private String item;
	@Digits(integer = 6,fraction = 2)
	private BigDecimal price;
	@JsonBackReference("user-order")
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;

	@CreationTimestamp
	@Basic
	//@Temporal(TemporalType.TIMESTAMP)
	private Timestamp siparisTarihi;
	@ManyToOne(fetch = FetchType.LAZY)
	private Siparis siparis;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getSiparisTarihi() {
		return siparisTarihi;
	}

	public void setSiparisTarihi(Timestamp siparisTarihi) {
		this.siparisTarihi = siparisTarihi;
	}

	public Siparis getSiparis() {
		return siparis;
	}

	public void setSiparis(Siparis siparis) {
		this.siparis = siparis;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Table_Orders that = (Table_Orders) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(restaurant, that.restaurant) &&
				Objects.equals(masa, that.masa) &&
				Objects.equals(item, that.item) &&
				Objects.equals(price, that.price) &&
				Objects.equals(user, that.user) &&
				Objects.equals(siparisTarihi, that.siparisTarihi) &&
				Objects.equals(siparis, that.siparis);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, restaurant, masa, item, price, user, siparisTarihi, siparis);
	}

	@Override
	public String toString() {
		return "Table_Orders{" +
				"id=" + id +
				", restaurant=" + restaurant +
				", masa=" + masa +
				", item='" + item + '\'' +
				", price=" + price +
				", user=" + user +
				", siparisTarihi=" + siparisTarihi +
				", order=" + siparis +
				'}';
	}
}
	
	
	
	

