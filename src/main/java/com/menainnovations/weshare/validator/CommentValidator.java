package com.menainnovations.weshare.validator;

import com.menainnovations.weshare.model.Comment;
import com.menainnovations.weshare.responses.CommentResponse;

public class CommentValidator {
    public static CommentResponse validateComment(Comment comment){
        CommentResponse response = new CommentResponse();
        response.setCommentContent(comment.getCommentContent());
        response.setCommentDate(comment.getCommentDate());
        response.setId(comment.getId());
        response.setUserId(comment.getUserId());
        return response;
    }
}
