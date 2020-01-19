package com.digimenu.main.domain.request;

import com.digimenu.main.domain.dto.MenuDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TableOrderRequest {
    private List<MenuDto> items=new ArrayList<>();
    private List<MenuDto> kampanya=new ArrayList<>();
    private Double latitude;
    private Double longitude;

    public List<MenuDto> getItems() {
        return items;
    }

    public void setItems(List<MenuDto> items) {
        this.items = items;
    }

    public List<MenuDto> getKampanya() {
        return kampanya;
    }

    public void setKampanya(List<MenuDto> kampanya) {
        this.kampanya = kampanya;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableOrderRequest that = (TableOrderRequest) o;
        return Objects.equals(items, that.items) &&
                Objects.equals(kampanya, that.kampanya) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, kampanya, latitude, longitude);
    }

    @Override
    public String toString() {
        return "TableOrderRequest{" +
                "items=" + items +
                ", kampanya=" + kampanya +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
