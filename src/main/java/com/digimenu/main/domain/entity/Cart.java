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
	
	private BigDecimal Price;
//	DB'de trigger sayesinde bu tabloya entity eklendiği için  Table_Orders'a bağlamaya gerek duymadık
//	onun yerine triggerda tableordersa eklenen restoran ve masa numarasını bu fieldlere atıyoruz.
	private Long restaurantId;
	private Integer masaNo;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date siparisTarihi;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date guncellemeTarihi;
	
	public Cart() {
	}

	public Cart(Long id, String item, BigDecimal Price, Long restaurantId, Integer masaNo, Date siparisTarihi,
				Date guncellemeTarihi) {
		this.id = id;
		this.item = item;
		this.Price = Price;
		this.restaurantId = restaurantId;
		this.masaNo = masaNo;
		this.siparisTarihi = siparisTarihi;
		this.guncellemeTarihi = guncellemeTarihi;
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

	public Date getGuncellemeTarihi() {
		return guncellemeTarihi;
	}

	public void setGuncellemeTarihi(Date guncellemeTarihi) {
		this.guncellemeTarihi = guncellemeTarihi;
	}

	public BigDecimal getPrice() {
		return Price;
	}

	public void setPrice(BigDecimal totalPrice) {
		this.Price = totalPrice;
	}

	public Date getSiparisTarihi() {
		return siparisTarihi;
	}

	public void setSiparisTarihi(Date siparisTarihi) {
		this.siparisTarihi = siparisTarihi;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Cart)) return false;
		Cart cart = (Cart) o;
		return Objects.equals(id, cart.id) &&
				Objects.equals(item, cart.item) &&
				Objects.equals(Price, cart.Price) &&
				Objects.equals(restaurantId, cart.restaurantId) &&
				Objects.equals(masaNo, cart.masaNo) &&
				Objects.equals(siparisTarihi, cart.siparisTarihi) &&
				Objects.equals(guncellemeTarihi, cart.guncellemeTarihi);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, item, Price, restaurantId, masaNo, siparisTarihi, guncellemeTarihi);
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", item=" + item + ", totalPrice=" + Price + ", restaurantId=" + restaurantId
				+ ", masaNo=" + masaNo + ", siparisTarihi=" + siparisTarihi + ", guncellemeTarihi=" + guncellemeTarihi
				+ "]";
	}
}
