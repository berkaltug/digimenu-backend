package com.digimenu.main.domain.dto;

import com.digimenu.main.domain.entity.Restaurant;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class MenuDto {

    private Long id;
    private String item;
    private String ingredients;
    private Double price;
    private String category;
    private String message;


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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuDto menuDto = (MenuDto) o;
        return Objects.equals(id, menuDto.id) &&
                Objects.equals(item, menuDto.item) &&
                Objects.equals(ingredients, menuDto.ingredients) &&
                Objects.equals(price, menuDto.price) &&
                Objects.equals(category, menuDto.category) &&
                Objects.equals(message, menuDto.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, ingredients, price, category, message);
    }

    @Override
    public String toString() {
        return "MenuDto{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
