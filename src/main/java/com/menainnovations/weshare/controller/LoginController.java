package com.menainnovations.weshare.controller;

import com.menainnovations.weshare.mobile.response.validator.UserValidator;
import com.menainnovations.weshare.mobileReponse.UserResponse;
import com.menainnovations.weshare.model.User;
import com.menainnovations.weshare.services.PostServiceImpl;
import com.menainnovations.weshare.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class LoginController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    PostServiceImpl postService;
    @RequestMapping(value = "/login/email" , method = RequestMethod.POST)
    public User login(@RequestBody User user2){
        User user =userService.getUserByEmail(user2.getEmail());
        try {
            String userPassword = user.getPassword();
            String password = user2.getPassword();
            if (!password.equals(userPassword)) {
                return null;
            } else {
                User response = userService.getUserById(user.getId());
                response.setPassword("");
                return response;
            }
        }catch (Exception e){
            return null;

        }
    }

    @RequestMapping(value = "/login/phone" , method = RequestMethod.POST)
    public UserResponse loginWithPhone(@RequestBody User user1){
            User user =userService.getUserByPhone(user1.getPhone());
            return UserValidator.validateUser(user);
        /*
        try {
            String psw=user.getPassword();
            String password=user1.getPassword();
            if(!password.equals(psw)){
                return null;
            }else {
                User response = userService.getUserById(user.getId());
                user.setPassword("");
                return response;
            }
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            return null;
        }
        */

    }
}