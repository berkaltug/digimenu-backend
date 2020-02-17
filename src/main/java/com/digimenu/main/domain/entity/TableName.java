package com.digimenu.main.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
public class TableName {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer masaNo;


    private String name;

    @JsonBackReference("table-name") //belki lazım olur ilerde
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        TableName tableName = (TableName) o;
        return Objects.equals(id, tableName.id) &&
                Objects.equals(masaNo, tableName.masaNo) &&
                Objects.equals(name, tableName.name) &&
                Objects.equals(restaurant, tableName.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, masaNo, name, restaurant);
    }

    @Override
    public String toString() {
        return "TableName{" +
                "id=" + id +
                ", masaNo=" + masaNo +
                ", name='" + name + '\'' +
                ", restaurant=" + restaurant +
                '}';
    }
}

