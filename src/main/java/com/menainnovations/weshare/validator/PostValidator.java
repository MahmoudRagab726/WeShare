package com.menainnovations.weshare.validator;

import com.menainnovations.weshare.model.Post;
import com.menainnovations.weshare.model.User;
import com.menainnovations.weshare.responses.CommentResponse;
import com.menainnovations.weshare.responses.PostResponse;

import java.util.ArrayList;
import java.util.List;

public class PostValidator {
    public static PostResponse validatePost(Post post){
        PostResponse response= new PostResponse();
        response.setCaseAddress(post.getCaseAddress());
        response.setCaseArea(post.getCaseArea().getAreaName());
        response.setCaseCity(post.getCaseCity().getCityName());
        response.setCaseContact(post.getCaseContact());
        List<CommentResponse> commentResponses=new ArrayList<CommentResponse>();
        for (int i=0;i<post.getComments().size();i++) {
            commentResponses.add(CommentValidator.validateComment(post.getComments().get(i)));
        }
        response.setComments(commentResponses);
        response.setCaseName(post.getCaseName());
        response.setId(post.getId());
        response.setPostDate(post.getPostDate());
        response.setPhotos(post.getPhotos());
        response.setTitle(post.getTitle());
        /*
            remove unused data from response of post
         */
        User user=post.getUser();
        user.setPosts(null);
        user.setPassword("");
        response.setUser(user);
        return response;
    }
}
