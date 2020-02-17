package com.digimenu.main.domain.dto;

import com.digimenu.main.domain.entity.Restaurant;

import java.util.Objects;

public class TableNameDto {
    public Integer masaNo;
    public String name;
    public Restaurant restaurant;

    public Integer getMasaNo() {
        return masaNo;
    }

    public void setMasaNo(Integer masaNo) {
        this.masaNo = masaNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableNameDto that = (TableNameDto) o;
        return Objects.equals(masaNo, that.masaNo) &&
                Objects.equals(name, that.name) &&
                Objects.equals(restaurant, that.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(masaNo, name, restaurant);
    }

    @Override
    public String toString() {
        return "TableNameDto{" +
                "masaNo=" + masaNo +
                ", name='" + name + '\'' +
                ", restaurant=" + restaurant +
                '}';
    }
}
