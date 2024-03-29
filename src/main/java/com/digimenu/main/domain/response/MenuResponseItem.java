package com.digimenu.main.domain.response;

import java.math.BigDecimal;
import java.util.Objects;

public class MenuResponseItem {
    private Long id;
    private String item;
    private String ingredients;
    private String options;
    private BigDecimal price;
    private String category;
    private String imagePublicId;
    private Float rating;

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

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImagePublicId() {
        return imagePublicId;
    }

    public void setImagePublicId(String imagePublicId) {
        this.imagePublicId = imagePublicId;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuResponseItem that = (MenuResponseItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(item, that.item) &&
                Objects.equals(ingredients, that.ingredients) &&
                Objects.equals(options, that.options) &&
                Objects.equals(price, that.price) &&
                Objects.equals(category, that.category) &&
                Objects.equals(imagePublicId, that.imagePublicId) &&
                Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, ingredients, options, price, category, imagePublicId, rating);
    }

    @Override
    public String toString() {
        return "MenuResponseItem{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", options='" + options + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", imagePublicId='" + imagePublicId + '\'' +
                ", rating=" + rating +
                '}';
    }
}
