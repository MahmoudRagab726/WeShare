package com.menainnovations.weshare.services;

import com.google.gson.Gson;
import com.menainnovations.weshare.mobileReponse.GetEnumeration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EnumerationService {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private FollowerServiceImpl followerService;
    @Autowired
    private CityServiceImpl cityService;

    private GetEnumeration getEnumerationData=new GetEnumeration();

    public GetEnumeration getEnumeration(long id){
        try {
            getEnumerationData.setUser(userService.getUserById(id));
            getEnumerationData.setCities(cityService.getAllCities());
            //getEnumerationData.setFollowers(followerService.getAllFollowers(id));
            //getEnumerationData.setFollowing(followerService.getAllFollowing(id));
            //getEnumerationData.setFollowersCount(followerService.getAllFollowers(id).size());
            //getEnumerationData.setFollowingCount(followerService.getAllFollowing(id).size());
            getEnumerationData.setStatus(1);
        }catch (Exception e){
            getEnumerationData.setStatus(0);
        }
    return getEnumerationData;
    }
}
