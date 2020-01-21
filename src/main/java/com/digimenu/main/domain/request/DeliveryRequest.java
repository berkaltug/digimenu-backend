package com.digimenu.main.domain.request;

import java.util.Objects;

public class DeliveryRequest {
    private Boolean value;
    private Long id;

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryRequest that = (DeliveryRequest) o;
        return Objects.equals(value, that.value) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, id);
    }

    @Override
    public String toString() {
        return "DeliveryRequest{" +
                "value=" + value +
                ", id=" + id +
                '}';
    }
}
