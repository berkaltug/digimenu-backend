package com.digimenu.main.controller;

import com.digimenu.main.domain.request.CommentRequest;
import com.digimenu.main.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PreAuthorize("hasRole('USER') OR hasRole('RESTAURANT') OR hasRole('ADMIN')")
    @PostMapping("/makeComment")
    public ResponseEntity<String> makeComment(@RequestBody CommentRequest commentRequest){
        commentService.makeRating(commentRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
