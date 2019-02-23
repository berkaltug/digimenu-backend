package com.digimenu.main.DTO;

public class Table_OrdersDTO {
	private Long id;
	private Long itemId;
	private Long cartId;
	public Table_OrdersDTO(Long id, Long itemId, Long cartId) {
		this.id = id;
		this.itemId = itemId;
		this.cartId = cartId;
	}
	public Table_OrdersDTO() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	
	
}
