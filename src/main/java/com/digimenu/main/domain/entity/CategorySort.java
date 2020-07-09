package com.digimenu.main.domain.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CategorySort {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    private Restaurant restaurant;
    @ManyToOne(fetch=FetchType.LAZY)
    private Category category;
    private Integer sortingNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getSortingNo() {
        return sortingNo;
    }

    public void setSortingNo(Integer sortingNo) {
        this.sortingNo = sortingNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategorySort that = (CategorySort) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(restaurant, that.restaurant) &&
                Objects.equals(category, that.category) &&
                Objects.equals(sortingNo, that.sortingNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, restaurant, category, sortingNo);
    }

    @Override
    public String toString() {
        return "CategorySort{" +
                "id=" + id +
                ", restaurant=" + restaurant +
                ", category=" + category +
                ", sortingNo=" + sortingNo +
                '}';
    }
    
}
