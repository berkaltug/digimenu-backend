package com.digimenu.main.domain.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PanelCampaignDto {
    private Long id;
    @NotEmpty(message = "İsim boş bırakılamaz")
    private String name;
    @NotEmpty(message = "içerik boş bırakılamaz")
    private String contents;

    @Digits(integer = 6,fraction = 2)
    private BigDecimal price;
    private Long restaurantId;
    @NotNull(message = "Null değer hatası")
    private Boolean isActive;
    private MultipartFile image;
    private Float rating;
    private Integer voteCount;
    private Boolean shouldDelImage;

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

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
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

    public Boolean getShouldDelImage() {
        return shouldDelImage;
    }

    public void setShouldDelImage(Boolean shouldDelImage) {
        this.shouldDelImage = shouldDelImage;
    }
}
