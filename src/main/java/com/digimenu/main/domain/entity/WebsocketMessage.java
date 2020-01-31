package com.digimenu.main.domain.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class WebsocketMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Lob
    private String message;

    private Long restaurantId;

    private Integer masaNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        WebsocketMessage message1 = (WebsocketMessage) o;
        return Objects.equals(id, message1.id) &&
                Objects.equals(message, message1.message) &&
                Objects.equals(restaurantId, message1.restaurantId) &&
                Objects.equals(masaNo, message1.masaNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, restaurantId, masaNo);
    }

    @Override
    public String toString() {
        return "WebsocketMessage{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", restaurantId=" + restaurantId +
                ", masaNo=" + masaNo +
                '}';
    }
}
