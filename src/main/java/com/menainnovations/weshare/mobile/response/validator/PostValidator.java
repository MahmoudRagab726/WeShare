package com.menainnovations.weshare.mobile.response.validator;

import com.menainnovations.weshare.mobileReponse.ListPostsResponse;
import com.menainnovations.weshare.mobileReponse.PostResponse;
import com.menainnovations.weshare.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostValidator {
    public static PostResponse validatePost(Post post){
        PostResponse response=new PostResponse();
        if (post!=null){
            response.setStatus(1);
            response.setPosts(post);
        }else {
            response.setStatus(0);
            response.setPosts(null);
        }
        return response;
    }

    public static ListPostsResponse validateListOfPosts(List<Post> posts){
        ListPostsResponse response=new ListPostsResponse();
        if(posts!=null||posts.size()>0){
            response.setStatus(1);
            response.setPosts(posts);
        }else {
            response.setStatus(0);
            response.setPosts(null);
        }
        return response;
    }
}
