package com.digimenu.main.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.digimenu.main.security.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull

    private String name;
    @JsonBackReference(value = "city-restaurant")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
    @NotNull
    private String address;
    @NotNull
    private Long tel;
    @NotNull
    private String mail;
    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    private Double latitude;
    private Double longitude;
    private Double radius;
	private String logoPublicId;

    @OneToMany(cascade=CascadeType.ALL,mappedBy = "restaurant")
	private List<Comment> comments=new ArrayList<>();

    @NotNull
    private Integer tableAmount;

    private Integer themeId;
	@Column(columnDefinition = "integer default 0")
	private Integer menuCounter;
	public Restaurant() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getTel() {
		return tel;
	}

	public void setTel(Long tel) {
		this.tel = tel;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}

	public Integer getTableAmount() {
		return tableAmount;
	}

	public void setTableAmount(Integer tableAmount) {
		this.tableAmount = tableAmount;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void addComment(Comment comment){
		comments.add(comment);
		comment.setRestaurant(this);
	}

	public void removeComment(Comment comment){
		comments.remove(comment);
		comment.setRestaurant(null);
	}

	public String getLogoPublicId() {
		return logoPublicId;
	}

	public void setLogoPublicId(String logoPublicId) {
		this.logoPublicId = logoPublicId;
	}

	public Integer getThemeId() {
		return themeId;
	}

	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
	}

	public Integer getMenuCounter() {
		return menuCounter;
	}

	public void setMenuCounter(Integer menuCounter) {
		this.menuCounter = menuCounter;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Restaurant that = (Restaurant) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(name, that.name) &&
				Objects.equals(city, that.city) &&
				Objects.equals(address, that.address) &&
				Objects.equals(tel, that.tel) &&
				Objects.equals(mail, that.mail) &&
				Objects.equals(owner, that.owner) &&
				Objects.equals(latitude, that.latitude) &&
				Objects.equals(longitude, that.longitude) &&
				Objects.equals(radius, that.radius) &&
				Objects.equals(logoPublicId, that.logoPublicId) &&
				Objects.equals(comments, that.comments) &&
				Objects.equals(tableAmount, that.tableAmount) &&
				Objects.equals(themeId, that.themeId) &&
				Objects.equals(menuCounter, that.menuCounter);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, city, address, tel, mail, owner, latitude, longitude, radius, logoPublicId, comments, tableAmount, themeId, menuCounter);
	}

	@Override
	public String toString() {
		return "Restaurant{" +
				"id=" + id +
				", name='" + name + '\'' +
				", city=" + city.getId() +
				", address='" + address + '\'' +
				", tel=" + tel +
				", mail='" + mail + '\'' +
				", owner=" + owner.getUsername() +
				", latitude=" + latitude +
				", longitude=" + longitude +
				", radius=" + radius +
				", tableAmount=" + tableAmount +
				", logoPublicId=" + logoPublicId +
				",themeId=" + themeId +
				",menuCounter=" +
				'}';
	}
}
