package com.menainnovations.weshare.validator;


import com.menainnovations.weshare.model.User;
import com.menainnovations.weshare.responses.UserResponse;

public class UserValidator {
   public static UserResponse validateUser(User user){
       UserResponse response=new UserResponse();
       response.setId(user.getId());
       response.setBio(user.getBio());
       response.setCity(user.getCity().getCityName());
       response.setEmail(user.getEmail());
       response.setName(user.getName());
       response.setGender(user.getGender());
       response.setPhone(user.getPhone());
       response.setPhoto(user.getPhoto());
       response.setStatus(user.getStatus());
       response.setPosts(user.getPosts());
       return response;
   }
}
