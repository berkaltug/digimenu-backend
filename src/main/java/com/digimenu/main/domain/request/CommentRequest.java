package com.digimenu.main.domain.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommentRequest {
    private List<RatingRequest> ratingRequests =new ArrayList<>();
    private String commentMessage;
    private Long restaurantId;
    private Long orderId;

    public List<RatingRequest> getRatingRequests() {
        return ratingRequests;
    }

    public void setRatingRequests(List<RatingRequest> ratingRequests) {
        this.ratingRequests = ratingRequests;
    }

    public String getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentRequest that = (CommentRequest) o;
        return Objects.equals(ratingRequests, that.ratingRequests) &&
                Objects.equals(commentMessage, that.commentMessage) &&
                Objects.equals(restaurantId, that.restaurantId) &&
                Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingRequests, commentMessage, restaurantId, orderId);
    }

    @Override
    public String toString() {
        return "CommentRequest{" +
                "ratingRequests=" + ratingRequests +
                ", commentMessage='" + commentMessage + '\'' +
                ", restaurantId=" + restaurantId +
                ", orderId=" + orderId +
                '}';
    }
}
