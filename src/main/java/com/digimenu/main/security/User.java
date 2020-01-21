package com.digimenu.main.security;

import com.digimenu.main.domain.entity.Table_Orders;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="user_table")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique=true,nullable=false,name="username")
	private String username;
	
	@NotNull
	private String name;
	
	@NotNull
	private String surname;
	
	@NotNull
	@Email
	@Column(unique=true)
	private String email;
	
	@NotNull
	private String password;
	
	private boolean isEnabled;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="user_role",
	joinColumns= {@JoinColumn(name="user_id")},
	inverseJoinColumns= {@JoinColumn(name="role_id")})
	private List<Role> roles;
	@JsonManagedReference("user-order")
	@OneToOne(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	Table_Orders order;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Table_Orders getOrder() {
		return order;
	}

	public void setOrder(Table_Orders order) {
		this.order = order;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return isEnabled == user.isEnabled &&
				Objects.equals(id, user.id) &&
				Objects.equals(username, user.username) &&
				Objects.equals(name, user.name) &&
				Objects.equals(surname, user.surname) &&
				Objects.equals(email, user.email) &&
				Objects.equals(password, user.password) &&
				Objects.equals(roles, user.roles) &&
				Objects.equals(order, user.order);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, username, name, surname, email, password, isEnabled, roles, order);
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", isEnabled=" + isEnabled +
				", roles=" + roles +
				'}';
	}
}
