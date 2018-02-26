package com.menainnovations.weshare.services;

import com.menainnovations.weshare.model.Follower;
import com.menainnovations.weshare.model.User;

import java.util.List;

public interface FollowerService {
    public void addFollower(Follower follower);
    public void deleteFollower(long followerId,long userId);
    public String getAllFollowers(long userId);
    public String getAllFollowing(long followerId);
    public Follower getTheRelationBetweenTwoUsers(long followerId , long userId);
}
