package com.digimenu.main.DTO;

import java.util.Date;
import java.util.List;

public class CartDTO {
	private List<Long> tableOrderIds;
	private Integer totalPrice;
	private Date siparisTarihi;
	public CartDTO( List<Long> tableOrderIds, Integer totalPrice, Date siparisTarihi) {
		this.tableOrderIds = tableOrderIds;
		this.totalPrice = totalPrice;
		this.siparisTarihi = siparisTarihi;
	}
	public CartDTO() {
	}
	public List<Long> getTableOrderIds() {
		return tableOrderIds;
	}
	public void setTableOrderIds(List<Long> tableOrderIds) {
		this.tableOrderIds = tableOrderIds;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getSiparisTarihi() {
		return siparisTarihi;
	}
	public void setSiparisTarihi(Date siparisTarihi) {
		this.siparisTarihi = siparisTarihi;
	}
	
	
}
