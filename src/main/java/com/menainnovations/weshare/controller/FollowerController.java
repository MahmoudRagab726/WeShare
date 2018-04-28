package com.menainnovations.weshare.controller;

import com.menainnovations.weshare.model.Follower;
import com.menainnovations.weshare.model.User;
import com.menainnovations.weshare.services.FollowerServiceImpl;
import com.menainnovations.weshare.validator.UserTokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class FollowerController {

    @Autowired
    FollowerServiceImpl followerService;
    @Autowired
    UserTokenValidator userTokenValidator;
    @RequestMapping(value = "/user/{userId}/followers" , method = RequestMethod.GET)
    public String getFollower(@RequestHeader("Access-Token") String token ,@PathVariable long userId){
        if(token.trim()==""){
            return "{\"status\": 400}";
        }else if (userTokenValidator.validateToken(userId,token)){
            return followerService.getAllFollowers(userId);
        }else {
            return "{\"status\": 400}";
        }
    }

    @RequestMapping(value = "/user/{followerId}/following" , method = RequestMethod.GET)
    public String getFollowing(@RequestHeader("Access-Token") String token ,@PathVariable long followerId){
        if(token.trim()==""){
            return "{\"status\": 400}";
        }else if (userTokenValidator.validateToken(followerId,token)){
            return followerService.getAllFollowing(followerId);
        }else {
            return "{\"status\": 400}";
        }

    }

    /*
    it will return true if the user follow the follower
     */

    @RequestMapping(value = "/user/{followerId}/follow/{userId}" , method = RequestMethod.GET)
    public String isFollowing(@RequestHeader("Access-Token") String token ,@PathVariable long userId ,@PathVariable long followerId){
        if(token.trim()==""){
            return "{\"status\": 400}";
        }else if (userTokenValidator.validateToken(followerId,token)){
            Follower follower= followerService.getTheRelationBetweenTwoUsers(followerId,userId);
            if (follower==null){
                return "{\"status\": 0}";
            }else {
                return "{\"status\": 1}";
            }
        }else {
            return "{\"status\": 400}";
        }



    }

    @RequestMapping(value = "/user/{followerId}/follow/{userId}" , method = RequestMethod.POST)
    public String addFollow(@RequestHeader("Access-Token") String token ,@PathVariable long followerId ,@PathVariable long userId){
        if(token.trim()==""){
            return "{\"status\": 400}";
        }else if (userTokenValidator.validateToken(followerId,token)){
            Follower follower=new Follower();
            follower.setFollowerId(followerId);
            follower.setUserId(userId);
            followerService.addFollower(follower);
        }else {
            return "{\"status\": 400}";
        }
        return "{\"status\": 1}";
    }

    @RequestMapping(value = "user/{followerId}/unfollow/{userId}" , method = RequestMethod.DELETE)
    public String deleteFollow(@RequestHeader("Access-Token") String token ,@PathVariable long followerId,@PathVariable long userId){
        if(token.trim()==""){
            return "{\"status\": 400}";
        }else if (userTokenValidator.validateToken(followerId,token)){
             followerService.deleteFollower(followerId,userId);
             return "{\"status\": 1}";
        }else {
            return "{\"status\": 400}";
        }

    }
}
