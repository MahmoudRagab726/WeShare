package com.menainnovations.weshare.services;


import com.menainnovations.weshare.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {

    public String addComment(long userId , long postId , Comment comment);
    public void deleteCommentById(long id);
    public void updateComment(long id,Comment comment);

}
