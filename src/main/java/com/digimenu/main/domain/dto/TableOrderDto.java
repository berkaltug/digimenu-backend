package com.digimenu.main.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TableOrderDto {
    private List<MenuDto> items=new ArrayList();
    private Long resId;
    private Integer masaNo;
    private Double latitude;
    private Double longitude;

    public List<MenuDto> getItems() {
        return items;
    }

    public void setItems(List<MenuDto> items) {
        this.items = items;
    }

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public Integer getMasaNo() {
        return masaNo;
    }

    public void setMasaNo(Integer masaNo) {
        this.masaNo = masaNo;
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
        TableOrderDto that = (TableOrderDto) o;
        return Objects.equals(items, that.items) &&
                Objects.equals(resId, that.resId) &&
                Objects.equals(masaNo, that.masaNo) &&
                Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, resId, masaNo, latitude, longitude);
    }

    @Override
    public String toString() {
        return "TableOrderDto{" +
                "items=" + items +
                ", resId=" + resId +
                ", masaNo=" + masaNo +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
