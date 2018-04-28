package com.menainnovations.weshare.mobileReponse;

import com.menainnovations.weshare.model.Photo;

import java.util.List;

public class ListPhotosResponse {
    private int status;
    private List<Photo> posts;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Photo> getPosts() {
        return posts;
    }

    public void setPosts(List<Photo> posts) {
        this.posts = posts;
    }
}
