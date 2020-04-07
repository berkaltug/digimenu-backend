package com.digimenu.main.domain.dto;

import com.digimenu.main.domain.dto.PastOrderDto;
import com.digimenu.main.domain.projection.PastOrdersProjection;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class PastOrdersResponseDto {

  private Long restaurantId;
  private String restaurantName;
  private Date orderDate;
  private List<PastOrderDto> orders;

  public PastOrdersResponseDto(Long restaurantId, String restaurantName, Date orderDate, List<PastOrderDto> orders) {
    this.restaurantId = restaurantId;
    this.restaurantName = restaurantName;
    this.orderDate = orderDate;
    this.orders = orders;
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
    return Objects.equals(restaurantId, that.restaurantId) &&
            Objects.equals(restaurantName, that.restaurantName) &&
            Objects.equals(orderDate, that.orderDate) &&
            Objects.equals(orders, that.orders);
  }

  @Override
  public int hashCode() {
    return Objects.hash(restaurantId, restaurantName, orderDate, orders);
  }

  @Override
  public String toString() {
    return "PastOrdersResponseDto{" +
            "restaurantId=" + restaurantId +
            ", restaurantName='" + restaurantName + '\'' +
            ", orderDate=" + orderDate +
            ", orders=" + orders +
            '}';
  }
}
