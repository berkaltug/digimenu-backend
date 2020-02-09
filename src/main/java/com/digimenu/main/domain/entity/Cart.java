package com.digimenu.main.domain.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String item;
	
	private Double price;
//	DB'de trigger sayesinde bu tabloya entity eklendiği için  Table_Orders'a bağlamaya gerek duymadık
//	onun yerine triggerda tableordersa eklenen restoran ve masa numarasını bu fieldlere atıyoruz.
	private Long restaurantId;
	private Integer masaNo;
	private String message;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date siparisTarihi;
	private Boolean isDelivered;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Integer getMasaNo() {
		return masaNo;
	}

	public void setMasaNo(Integer masaNo) {
		this.masaNo = masaNo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSiparisTarihi() {
		return siparisTarihi;
	}

	public void setSiparisTarihi(Date siparisTarihi) {
		this.siparisTarihi = siparisTarihi;
	}

	public Boolean getDelivered() {
		return isDelivered;
	}

	public void setDelivered(Boolean delivered) {
		isDelivered = delivered;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cart cart = (Cart) o;
		return Objects.equals(id, cart.id) &&
				Objects.equals(item, cart.item) &&
				Objects.equals(price, cart.price) &&
				Objects.equals(restaurantId, cart.restaurantId) &&
				Objects.equals(masaNo, cart.masaNo) &&
				Objects.equals(message, cart.message) &&
				Objects.equals(siparisTarihi, cart.siparisTarihi) &&
				Objects.equals(isDelivered, cart.isDelivered);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, item, price, restaurantId, masaNo, message, siparisTarihi, isDelivered);
	}

	@Override
	public String toString() {
		return "Cart{" +
				"id=" + id +
				", item='" + item + '\'' +
				", Price=" + price +
				", restaurantId=" + restaurantId +
				", masaNo=" + masaNo +
				", message='" + message + '\'' +
				", siparisTarihi=" + siparisTarihi +
				", isDelivered=" + isDelivered +
				'}';
	}
}

