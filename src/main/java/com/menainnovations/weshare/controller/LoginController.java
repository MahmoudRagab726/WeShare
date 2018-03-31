package com.menainnovations.weshare.controller;

import com.menainnovations.weshare.exception.LoginFailureException;
import com.menainnovations.weshare.model.Post;
import com.menainnovations.weshare.model.User;
import com.menainnovations.weshare.services.PostServiceImpl;
import com.menainnovations.weshare.services.UserService;
import com.menainnovations.weshare.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class LoginController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    PostServiceImpl postService;
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public List<Post> login(@RequestBody User user2){
        User user =userService.getUserByEmail(user2.getEmail());
        try {
            String userPassword = user.getPassword();
            String password = user2.getPassword();
            if (!password.equals(userPassword)) {
                return null;
            } else {
                return postService.getPostsByFollower(user.getId());
            }
        }catch (Exception e){
            return null;

        }
    }
}