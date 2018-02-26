package com.menainnovations.weshare.controller;

import com.menainnovations.weshare.model.Follower;
import com.menainnovations.weshare.model.User;
import com.menainnovations.weshare.services.FollowerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class FollowerController {

    @Autowired
    FollowerServiceImpl followerService;

    @RequestMapping(value = "/user/{userId}/followers" , method = RequestMethod.GET)
    public String getFollower(@PathVariable long userId){
        return followerService.getAllFollowers(userId);
    }

    @RequestMapping(value = "/user/{followerId}/following" , method = RequestMethod.GET)
    public String getFollowing(@PathVariable long followerId){
        return followerService.getAllFollowing(followerId);
    }

    /*
    it will return true if the user follow the follower
     */

    @RequestMapping(value = "/user/{followerId}/follow/{userId}" , method = RequestMethod.GET)
    public boolean isFollowing(@PathVariable long userId ,@PathVariable long followerId){
        Follower follower= followerService.getTheRelationBetweenTwoUsers(followerId,userId);
        if (follower==null){
            return false;
        }else {
            return true;
        }
    }

    @RequestMapping(value = "/user/{followerId}/follow/{userId}" , method = RequestMethod.POST)
    public void addFollow(@PathVariable long followerId ,@PathVariable long userId){
        Follower follower=new Follower();
        follower.setFollowerId(followerId);
        follower.setUserId(userId);
        followerService.addFollower(follower);
    }

    @RequestMapping(value = "user/{followerId}/unfollow/{userId}" , method = RequestMethod.DELETE)
    public void deleteFollow(@PathVariable long followerId,@PathVariable long userId){
        followerService.deleteFollower(followerId,userId);
    }
}
