package com.digimenu.main.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import javax.persistence.Column;
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
	
	private Float totalPrice;
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

	public Cart(Long id, String item, Float totalPrice, Long restaurantId, Integer masaNo, Date siparisTarihi,
			Date guncellemeTarihi) {
		this.id = id;
		this.item = item;
		this.totalPrice = totalPrice;
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

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getSiparisTarihi() {
		return siparisTarihi;
	}

	public void setSiparisTarihi(Date siparisTarihi) {
		this.siparisTarihi = siparisTarihi;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", item=" + item + ", totalPrice=" + totalPrice + ", restaurantId=" + restaurantId
				+ ", masaNo=" + masaNo + ", siparisTarihi=" + siparisTarihi + ", guncellemeTarihi=" + guncellemeTarihi
				+ "]";
	}
}
