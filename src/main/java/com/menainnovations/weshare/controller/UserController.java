package com.menainnovations.weshare.controller;

import com.google.gson.Gson;
import com.menainnovations.weshare.mobile.response.validator.UserValidator;
import com.menainnovations.weshare.mobileReponse.UserResponse;
import com.menainnovations.weshare.model.User;
import com.menainnovations.weshare.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {
    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/api/user/{id}" , method = RequestMethod.GET)
    public UserResponse getUser(@PathVariable  long id){
        User user =userService.getUserById(id);
        return UserValidator.validateUser(user);
    }

    @RequestMapping(value = "/users" , method = RequestMethod.GET , produces = "application/json")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/api/user" , method = RequestMethod.POST)
    public String addUser(@RequestBody User user){
       return userService.addUser(user);
    }
    @RequestMapping(value = "/api/user/{id}"  , method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable long id){
        return userService.deleteUserById(id);
    }
    @RequestMapping(value = "/api/user/{id}"  , method = RequestMethod.PUT)
    public String updateUser(@PathVariable long id , @RequestBody User user){
        return userService.updateUser(id,user);
    }

}
