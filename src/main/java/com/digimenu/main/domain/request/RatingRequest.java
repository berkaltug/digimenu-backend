package com.digimenu.main.domain.request;

import java.util.Objects;

public class RatingRequest {
    private String itemName;
    private Integer rating;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatingRequest that = (RatingRequest) o;
        return Objects.equals(itemName, that.itemName) &&
                Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, rating);
    }

    @Override
    public String toString() {
        return "RatingRequest{" +
                "id=" + itemName +
                ", rating=" + rating +
                '}';
    }


}
