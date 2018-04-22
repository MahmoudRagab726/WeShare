package com.menainnovations.weshare.mobileReponse;

import com.menainnovations.weshare.model.Post;

import java.util.List;

public class PostResponse {
    private int status ;
    private Post post;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Post getPost() {
        return post;
    }

    public void setPosts(Post post) {
        this.post = post;
    }
}
