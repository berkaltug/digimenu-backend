package com.digimenu.main.domain.dto;

import org.springframework.stereotype.Component;

@Component("restaurantDto")
public class RestaurantDto {
	
	private String username;
	private String password;
	public RestaurantDto(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public RestaurantDto() {
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
