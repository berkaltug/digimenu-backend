package com.digimenu.main.security;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PasswordResetToken {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String token;
	
	@OneToOne
	@JoinColumn(nullable=false,name="user_id")
	private User user;

	
	

	public PasswordResetToken(User user) {
		this.user = user;
		this.token=UUID.randomUUID().toString();
	}


	public PasswordResetToken() {
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
	
}
