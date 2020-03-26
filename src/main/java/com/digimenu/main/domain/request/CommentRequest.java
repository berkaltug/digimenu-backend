package com.digimenu.main.domain.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommentRequest {
    private List<RatingRequest> ratingRequests =new ArrayList<>();
    private String commentMessage;
    private Long restaurantId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentRequest that = (CommentRequest) o;
        return Objects.equals(ratingRequests, that.ratingRequests) &&
                Objects.equals(commentMessage, that.commentMessage) &&
                Objects.equals(restaurantId, that.restaurantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingRequests, commentMessage, restaurantId);
    }

    @Override
    public String toString() {
        return "CommentRequest{" +
                "ratingRequest=" + ratingRequests +
                ", commentMessage='" + commentMessage + '\'' +
                ", restaurantId=" + restaurantId +
                '}';
    }
}
