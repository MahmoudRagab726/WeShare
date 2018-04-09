package com.menainnovations.weshare.controller;

import com.google.gson.Gson;
import com.menainnovations.weshare.model.Post;
import com.menainnovations.weshare.model.User;
import com.menainnovations.weshare.responses.PostResponse;
import com.menainnovations.weshare.services.PhotoService;
import com.menainnovations.weshare.services.PostService;
import com.menainnovations.weshare.services.PostServiceImpl;
import com.menainnovations.weshare.validator.PostValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class PostController {
    @Autowired
    PostServiceImpl postService;
    @RequestMapping(value = "/post/{id}" , method = RequestMethod.GET)
    public PostResponse getPostById(@PathVariable long id){
        return PostValidator.validatePost(postService.getPostById(id));
    }
    @RequestMapping(value = "/search" , method = RequestMethod.GET)
    public List<Post> getPostByAreaOrCityOrBoth(@RequestParam("area") String area , @RequestParam("city") String city){
        if(area.trim()==""&&city.trim()==""){
           return postService.getAllPosts();
        }else if (area.trim()==""&&city.trim()!=""){
            return postService.getPostByCity(city);
        }else if (area.trim()!=""&&city.trim()==""){
            return postService.getPostByArea(area);
        }else {
            return postService.getPostByAreaAndCity(area,city);
        }
    }

    @RequestMapping(value = "/user/{userId}/posts" , method = RequestMethod.GET)
    public List<Post> getAllPosts(@PathVariable long userId){
        return postService.getAllPostsByUserId(userId);
    }

    /*
    this method for getting all posts for people that a user with User_Id follow them
     */
    @RequestMapping(value = "/user/{userId}/home" , method = RequestMethod.GET)
    public List<Post> getPostFollowers(@PathVariable long userId){
        return postService.getPostsByFollower(userId);
    }

    @RequestMapping(value = "/user/{userId}/post" , method = RequestMethod.POST)
    public String addPost(@PathVariable long userId , @RequestBody Post post){
            return postService.addPost(userId, post);
    }

    @RequestMapping(value = "/post/{id}"  , method = RequestMethod.DELETE)
    public String deletePost(@PathVariable long id){
        Post post =postService.getPostById(id);
        if(post ==null){
            return "fail";
        }else {
            postService.deletePostById(id);
            return "success";
        }
    }

    @RequestMapping(value = "/post/{id}"  , method = RequestMethod.PUT)
    public String updatePost(@PathVariable long id , @RequestBody Post post){
                return postService.updatePost(id,post);
    }


}
