package com.menainnovations.weshare.mobile.response.validator;

import com.menainnovations.weshare.mobileReponse.UserResponse;
import com.menainnovations.weshare.model.User;

public class UserValidator {
    public static UserResponse validateUser(User user){
        UserResponse response = new UserResponse();
        if (user!=null){
            response.setStatus(1);
            response.setUser(user);
        }else {
            response.setStatus(0);
        }
        return response;
    }

}
