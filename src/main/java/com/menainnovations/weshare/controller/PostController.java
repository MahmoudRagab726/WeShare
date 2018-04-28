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
import com.menainnovations.weshare.validator.UserTokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class PostController {
    @Autowired
    PostServiceImpl postService;
    @Autowired
    UserTokenValidator userTokenValidator;

    @RequestMapping(value = "/post/{id}" , method = RequestMethod.GET)
    public PostResponse getPostById(@RequestHeader("Access-Token") String token ,@PathVariable long id){
        PostResponse response=new PostResponse();
        if(token.trim()==""){
            response.setStatus(400);
            return response;
        }else if (userTokenValidator.validateToken(null,token)){
            return PostValidator.validatePost(postService.getPostById(id));
        }else {
            response.setStatus(400);
            return response;
        }

    }
    @RequestMapping(value = "/search" , method = RequestMethod.GET)
    public ListPostsResponse getPostByAreaOrCityOrBoth(@RequestHeader("Access-Token") String token ,@RequestParam("city") String city , @RequestParam("area") String area){
       ListPostsResponse response=new ListPostsResponse();
        if(token.trim()==""){
            response.setStatus(400);
            return response;
        }else if (userTokenValidator.validateToken(null,token)){
            if(area.trim()==""&&city.trim()==""){
                return PostValidator.validateListOfPosts(postService.getAllPosts());
            }else if (area.trim()==""&&city.trim()!=""){
                return PostValidator.validateListOfPosts(postService.getPostByCity(city));
            }else if (area.trim()!=""&&city.trim()==""){
                return PostValidator.validateListOfPosts(postService.getPostByArea(area));
            }else {
                return PostValidator.validateListOfPosts(postService.getPostByAreaAndCity(area,city));
            }
            }else {
            response.setStatus(400);
            return response;
        }



    }

    @RequestMapping(value = "/user/{userId}/posts" , method = RequestMethod.GET)
    public ListPostsResponse getAllPosts(@RequestHeader("Access-Token") String token ,@PathVariable long userId){
        ListPostsResponse response=new ListPostsResponse();
        if(token.trim()==""){
            response.setStatus(400);
            return response;
        }else if (userTokenValidator.validateToken(userId,token)){
            return PostValidator.validateListOfPosts(postService.getAllPostsByUserId(userId));
        }else {
            response.setStatus(400);
            return response;
        }

    }

    /*
    this method for getting all posts for people that a user with User_Id follow them
     */
    @RequestMapping(value = "/user/{userId}/home" , method = RequestMethod.GET)
    public ListPostsResponse getPostFollowers(@RequestHeader("Access-Token") String token ,@PathVariable long userId){
       ListPostsResponse response=new ListPostsResponse();
        if(token.trim()==""){
            response.setStatus(400);
            return response;
        }else if (userTokenValidator.validateToken(userId,token)){
            return PostValidator.validateListOfPosts(postService.getPostsByFollower(userId));
        }else {
            response.setStatus(400);
            return response;
        }

    }

    @RequestMapping(value = "/user/{userId}/post" , method = RequestMethod.POST)
    public String addPost(@RequestHeader("Access-Token") String token ,@PathVariable long userId , @RequestBody Post post){
        if(token.trim()==""){
            return "{\"status\": 400}";
        }else if (userTokenValidator.validateToken(userId,token)){
            return postService.addPost(userId, post);
        }else {
            return "{\"status\": 400}";
        }


    }

    @RequestMapping(value = "/post/{id}"  , method = RequestMethod.DELETE)
    public String deletePost(@RequestHeader("Access-Token") String token ,@PathVariable long id){
        if(token.trim()==""){
            return "{\"status\": 400}";
        }else if (userTokenValidator.validateToken(null,token)){
            Post post =postService.getPostById(id);
            if(post ==null){
                return "{\"status\": 0}";
            }else {
                postService.deletePostById(id);
                return "{\"status\": 1}";
            }
        }else {
            return "{\"status\": 400}";
        }

    }

    @RequestMapping(value = "/post/{id}"  , method = RequestMethod.PUT)
    public String updatePost(@RequestHeader("Access-Token") String token ,@PathVariable long id , @RequestBody Post post){
        if(token.trim()==""){
            return "{\"status\": 400}";
        }else if (userTokenValidator.validateToken(null,token)){
            return postService.updatePost(id,post);
        }else {
            return "{\"status\": 400}";
        }

    }


}
