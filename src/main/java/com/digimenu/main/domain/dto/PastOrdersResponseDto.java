package com.digimenu.main.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PastOrdersResponseDto {
  private Long orderId;
  private Long restaurantId;
  private String restaurantName;
  private Date orderDate;
  private boolean isVoted;
  private List<PastOrderDto> orders;

  public PastOrdersResponseDto(Long orderId, Long restaurantId, String restaurantName, Date orderDate, boolean isVoted, List<PastOrderDto> orders) {
    this.orderId = orderId;
    this.restaurantId = restaurantId;
    this.restaurantName = restaurantName;
    this.orderDate = orderDate;
    this.isVoted = isVoted;
    this.orders = orders;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
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
  @JsonProperty("isVoted")
  public boolean isVoted() {
    return isVoted;
  }

  public void setVoted(boolean voted) {
    isVoted = voted;
  }

  public List<PastOrderDto> getOrders() {
    return orders;
  }

  public void setOrders(List<PastOrderDto> orders) {
    this.orders = orders;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PastOrdersResponseDto that = (PastOrdersResponseDto) o;
    return isVoted == that.isVoted &&
            Objects.equals(orderId, that.orderId) &&
            Objects.equals(restaurantId, that.restaurantId) &&
            Objects.equals(restaurantName, that.restaurantName) &&
            Objects.equals(orderDate, that.orderDate) &&
            Objects.equals(orders, that.orders);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, restaurantId, restaurantName, orderDate, isVoted, orders);
  }

  @Override
  public String toString() {
    return "PastOrdersResponseDto{" +
            "orderId=" + orderId +
            ", restaurantId=" + restaurantId +
            ", restaurantName='" + restaurantName + '\'' +
            ", orderDate=" + orderDate +
            ", isVoted=" + isVoted +
            ", orders=" + orders +
            '}';
  }
}
