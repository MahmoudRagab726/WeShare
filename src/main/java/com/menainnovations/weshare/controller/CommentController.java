package com.menainnovations.weshare.controller;

import com.menainnovations.weshare.model.Comment;
import com.menainnovations.weshare.services.CommentService;
import com.menainnovations.weshare.services.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CommentController {

    @Autowired
    CommentServiceImpl commentService;



    @RequestMapping(value = "/user/{userId}/post/{postId}/comment" , method = RequestMethod.POST)
    public String addComment(@PathVariable long userId , @PathVariable long postId , @RequestBody Comment comment){
        //comment.setUserId(userId);
        return commentService.addComment(userId, postId, comment);
    }

    @RequestMapping(value = "/comment/{id}"  , method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable long id){
        commentService.deleteCommentById(id);

    }

    @RequestMapping(value = "/comment/{id}"  , method = RequestMethod.PUT)
    public void updatePost(@PathVariable long id , @RequestBody Comment comment){
        commentService.updateComment(id,comment);

    }

}
