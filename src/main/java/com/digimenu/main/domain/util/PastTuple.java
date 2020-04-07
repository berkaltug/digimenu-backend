package com.digimenu.main.domain.util;

import java.util.Date;
import java.util.Objects;

public class PastTuple {
    private Long restaurantId;
    private String restaurantName;
    private Date orderDate;

    public PastTuple(Long restaurantId, String restaurantName, Date orderDate) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.orderDate = orderDate;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PastTuple pastTuple = (PastTuple) o;
        return Objects.equals(restaurantId, pastTuple.restaurantId) &&
                Objects.equals(restaurantName, pastTuple.restaurantName) &&
                Objects.equals(orderDate, pastTuple.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, restaurantName, orderDate);
    }

    @Override
    public String toString() {
        return "PastTuple{" +
                "restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
