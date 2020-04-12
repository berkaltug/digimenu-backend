package com.digimenu.main.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    @NotEmpty(message = "İsim boş bırakılamaz")
    private String name;
    @NotEmpty(message = "içerik boş bırakılamaz")
    private String contents;

    @Digits(integer = 6,fraction = 2)
    private BigDecimal price;
    @JsonBackReference(value="restaurant-campaign")  //json ignore görevi gördü karşı bir @jsonMaagedRef olmamasına rağmen
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;
    @NotNull(message = "Null değer hatası")
    private Boolean isActive;

    private Float rating;
    private Integer voteCount;

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

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campaign campaign = (Campaign) o;
        return Objects.equals(id, campaign.id) &&
                Objects.equals(name, campaign.name) &&
                Objects.equals(contents, campaign.contents) &&
                Objects.equals(price, campaign.price) &&
                Objects.equals(restaurant, campaign.restaurant) &&
                Objects.equals(isActive, campaign.isActive) &&
                Objects.equals(rating, campaign.rating) &&
                Objects.equals(voteCount, campaign.voteCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contents, price, restaurant, isActive, rating, voteCount);
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contents='" + contents + '\'' +
                ", price=" + price +
                ", restaurant=" + restaurant.getId() +
                ", isActive=" + isActive +
                ", rating=" + rating +
                ", voteCount=" + voteCount +
                '}';
    }
}
