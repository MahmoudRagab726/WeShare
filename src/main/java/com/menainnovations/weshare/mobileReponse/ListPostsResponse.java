package com.menainnovations.weshare.mobileReponse;

import com.menainnovations.weshare.model.Post;

import java.util.List;

public class ListPostsResponse {
    private int status;
    private List<Post> posts;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
