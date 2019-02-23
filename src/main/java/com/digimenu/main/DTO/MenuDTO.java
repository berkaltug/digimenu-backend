package com.digimenu.main.DTO;

public class MenuDTO {
	private String item;
	private String ingredients;
	private Integer price;
	private String category;
	public MenuDTO( String item, String ingredients, Integer price, String category) {
		this.item = item;
		this.ingredients = ingredients;
		this.price = price;
		this.category = category;
	}
	public MenuDTO() {
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
