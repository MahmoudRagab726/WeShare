package com.menainnovations.weshare.controller;

import com.menainnovations.weshare.mobile.response.validator.UserValidator;
import com.menainnovations.weshare.mobileReponse.UserResponse;
import com.menainnovations.weshare.model.User;
import com.menainnovations.weshare.services.UserServiceImpl;
import com.menainnovations.weshare.validator.UserTokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserTokenValidator userTokenValidator;

    @RequestMapping(value = "/api/user/{id}" , method = RequestMethod.GET)
    public UserResponse getUser(@RequestHeader("Access-Token") String token ,@PathVariable  long id){
        UserResponse response=new UserResponse();
        if(token.trim()==""){
            response.setStatus(400);
            return response;
        }else if (userTokenValidator.validateToken(id,token)){
            User user =userService.getUserById(id);
            return UserValidator.validateUser(user);
        }else {
            response.setStatus(400);
            return response;
        }

    }
/*
    @RequestMapping(value = "/users" , method = RequestMethod.GET , produces = "application/json")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
*/
    @RequestMapping(value = "/api/user" , method = RequestMethod.POST)
    public String addUser(@RequestBody User user){
       return userService.addUser(user);
    }
    @RequestMapping(value = "/api/user/{id}"  , method = RequestMethod.DELETE)
    public String deleteUser(@RequestHeader("Access-Token") String token ,@PathVariable long id){
        if(token.trim()==""){
            return "{\"status\": 400}";
        }else if (userTokenValidator.validateToken(id,token)){
            return userService.deleteUserById(id);
        }else {
            return "{\"status\": 400}";
        }

    }
    @RequestMapping(value = "/api/user/{id}"  , method = RequestMethod.PUT)
    public String updateUser(@RequestHeader("Access-Token") String token ,@PathVariable long id , @RequestBody User user){
        if(token.trim()==""){
            return "{\"status\": 400}";
        }else if (userTokenValidator.validateToken(id,token)){
            return userService.updateUser(id,user);
        }else {
            return "{\"status\": 400}";
        }

    }

}
