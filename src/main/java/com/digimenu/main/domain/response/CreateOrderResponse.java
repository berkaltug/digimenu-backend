package com.digimenu.main.domain.response;

import com.digimenu.main.domain.dto.MessageDto;

import java.util.Objects;

public class CreateOrderResponse {
    private String restaurantOwner;
    private MessageDto socketMessage;

    public String getRestaurantOwner() {
        return restaurantOwner;
    }

    public void setRestaurantOwner(String restaurantOwner) {
        this.restaurantOwner = restaurantOwner;
    }

    public MessageDto getSocketMessage() {
        return socketMessage;
    }

    public void setSocketMessage(MessageDto socketMessage) {
        this.socketMessage = socketMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateOrderResponse that = (CreateOrderResponse) o;
        return Objects.equals(restaurantOwner, that.restaurantOwner) &&
                Objects.equals(socketMessage, that.socketMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantOwner, socketMessage);
    }

    @Override
    public String toString() {
        return "CreateOrderResponse{" +
                "restaurantOwner='" + restaurantOwner + '\'' +
                ", socketMessage=" + socketMessage +
                '}';
    }
}

