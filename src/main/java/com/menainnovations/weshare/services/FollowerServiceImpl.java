package com.menainnovations.weshare.services;

import com.menainnovations.weshare.model.Follower;
import com.menainnovations.weshare.model.User;
import com.menainnovations.weshare.repository.FollowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowerServiceImpl implements FollowerService{

    @Autowired
    FollowerRepository followerRepository;

    @Override
    public void addFollower(Follower follower) {

        followerRepository.save(follower);

    }

    @Override
    public void deleteFollower(long followerId,long userId) {
        followerRepository.deleteFollowerByFollowerIdAndUserId(followerId,userId);
    }

    @Override
    public String getAllFollowers(long userId) {
        return followerRepository.findAllByUserId(userId);
    }

    @Override
    public String getAllFollowing(long followerId) {
        return followerRepository.findAllByFollowerId(followerId);
    }

    @Override
    public Follower getTheRelationBetweenTwoUsers(long followerId, long userId) {
        return followerRepository.findAllByFollowerIdAndUserId(followerId,userId);
    }
}
