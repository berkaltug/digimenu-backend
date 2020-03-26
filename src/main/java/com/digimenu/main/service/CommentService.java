package com.digimenu.main.service;

import com.digimenu.main.domain.request.CommentRequest;

public interface CommentService {
    void makeRating(CommentRequest request);
}
