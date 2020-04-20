package com.digimenu.main.service;

import com.digimenu.main.domain.entity.Comment;
import com.digimenu.main.domain.request.CommentRequest;

import java.util.List;

public interface CommentService {
    void makeRating(CommentRequest request);
    List<Comment> getRestaurantComments();
}
