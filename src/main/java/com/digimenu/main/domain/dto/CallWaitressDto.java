package com.digimenu.main.domain.dto;

import java.util.Objects;

public class CallWaitressDto {
    Double latitude;
    Double longitude;
    Long restaurantId;
    Integer masaNo;

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

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getMasaNo() {
        return masaNo;
    }

    public void setMasaNo(Integer masaNo) {
        this.masaNo = masaNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallWaitressDto that = (CallWaitressDto) o;
        return Objects.equals(latitude, that.latitude) &&
                Objects.equals(longitude, that.longitude) &&
                Objects.equals(restaurantId, that.restaurantId) &&
                Objects.equals(masaNo, that.masaNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, restaurantId, masaNo);
    }

    @Override
    public String toString() {
        return "CallWaitressDto{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", restaurantId=" + restaurantId +
                ", masaNo=" + masaNo +
                '}';
    }
}
