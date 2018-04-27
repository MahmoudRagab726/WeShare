package com.menainnovations.weshare.controller;

import com.google.gson.Gson;
import com.menainnovations.weshare.mobile.response.validator.PostValidator;
import com.menainnovations.weshare.mobileReponse.ListPostsResponse;
import com.menainnovations.weshare.mobileReponse.PostResponse;
import com.menainnovations.weshare.model.Post;
import com.menainnovations.weshare.model.User;
import com.menainnovations.weshare.services.PhotoService;
import com.menainnovations.weshare.services.PostService;
import com.menainnovations.weshare.services.PostServiceImpl;
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
    public ListPostsResponse getPostByAreaOrCityOrBoth(@RequestParam("city") String city , @RequestParam("area") String area){
        if(area.trim()==""&&city.trim()==""){
           return PostValidator.validateListOfPosts(postService.getAllPosts());
        }else if (area.trim()==""&&city.trim()!=""){
            return PostValidator.validateListOfPosts(postService.getPostByCity(city));
        }else if (area.trim()!=""&&city.trim()==""){
            return PostValidator.validateListOfPosts(postService.getPostByArea(area));
        }else {
            return PostValidator.validateListOfPosts(postService.getPostByAreaAndCity(area,city));
        }
    }

    @RequestMapping(value = "/user/{userId}/posts" , method = RequestMethod.GET)
    public ListPostsResponse getAllPosts(@PathVariable long userId){
        return PostValidator.validateListOfPosts(postService.getAllPostsByUserId(userId));
    }

    /*
    this method for getting all posts for people that a user with User_Id follow them
     */
    @RequestMapping(value = "/user/{userId}/home" , method = RequestMethod.GET)
    public ListPostsResponse getPostFollowers(@PathVariable long userId){
        return PostValidator.validateListOfPosts(postService.getPostsByFollower(userId));
    }

    @RequestMapping(value = "/user/{userId}/post" , method = RequestMethod.POST)
    public String addPost(@PathVariable long userId , @RequestBody Post post){
            return postService.addPost(userId, post);
    }

    @RequestMapping(value = "/post/{id}"  , method = RequestMethod.DELETE)
    public String deletePost(@PathVariable long id){
        Post post =postService.getPostById(id);
        if(post ==null){
            return "{\"status\": 0}";
        }else {
            postService.deletePostById(id);
            return "{\"status\": 1}";
        }
    }

    @RequestMapping(value = "/post/{id}"  , method = RequestMethod.PUT)
    public String updatePost(@PathVariable long id , @RequestBody Post post){
                return postService.updatePost(id,post);
    }


}
