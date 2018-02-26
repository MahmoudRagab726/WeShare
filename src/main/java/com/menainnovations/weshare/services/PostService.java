package com.menainnovations.weshare.services;

import com.menainnovations.weshare.model.Post;

import java.util.List;

public interface PostService {
    public Post getPostById(long id);
    public List<Post> getPostByArea(String area);
    public List<Post> getPostByCity(String city);
    public List<Post> getPostByAreaAndCity(String area , String city);
    public List<Post> getAllPostsByUserId(long userId);
    public List<Post> getAllPosts();
    public String addPost(long userId,Post post);
    public void deletePostById(long id);
    public String updatePost(long id,Post post);
}
