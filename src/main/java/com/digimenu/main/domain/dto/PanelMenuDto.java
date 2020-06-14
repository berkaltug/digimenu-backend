package com.digimenu.main.domain.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class PanelMenuDto {

    private Long id;
    @NotEmpty(message = "Ürün ismi boş bırakılamaz")
    private String item;
    private Long restaurantId;
    private String ingredients;
    @NotNull(message = "Ücret alanı boş bırakılamaz")
    @Digits(integer=6, fraction=2)
    private BigDecimal price;
    private Float rating;
    private Integer voteCount;
    private String category;
    private MultipartFile image;
    private Boolean shouldDelImage;
    private Boolean isActive=true;
    private Boolean isFavourite=false;

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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Boolean getShouldDelImage() {
        return shouldDelImage;
    }

    public void setShouldDelImage(Boolean shouldDelImage) {
        this.shouldDelImage = shouldDelImage;
    }
}
