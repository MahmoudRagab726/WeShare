package com.menainnovations.weshare.services;

import com.menainnovations.weshare.model.User;
import com.menainnovations.weshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    /*
        get a user by Id
        return only one user per request
     */
    @Override
    public User getUserById(long id){
        User user =userRepository.findUserById(id);
        if(user!=null)
        return user;
        else return null;
    }
    /*
        get all users
        ignore this service now
     */
    @Override
    public List<User> getAllUsers(){
        PageRequest request = new PageRequest(1 - 1, 10, Sort.Direction.ASC, "id");
        return (List<User>) userRepository.findAll(request).getContent();
    }
    /*
        add new user
     */

    @Override
    public String addUser(User user){
        long userId=0;
        if(user.getName().trim()==null || user.getName()==""){
            return "{\"status\": \"fail\"" +
                    ", \"userId\":}";
        }else if (user.getEmail().trim()==null || user.getEmail()==""){
            return "{\"status\": \"fail\"" +
                    ", \"userId\":}";
        }else if(!(getUserByEmail(user.getEmail())==null)){
            return "{\"status\": \"fail\"" +
                    ", \"userId\":}";
        }else if (user.getPassword().trim()==null || user.getPassword()=="" ||user.getPassword().length()<10){
            return "{\"status\": \"fail\"" +
                    ", \"userId\":}";
        }else if (user.getGender()==null){
            return "{\"status\": \"fail\"" +
                    ", \"userId\":}";
        }else {
            try {
                userId = userRepository.save(user).getId();
            }catch (Exception ex){
                return "{\"status\": \"fail\"" +
                        ", \"userId\":}";
            }
            return "{\"status\": \"success\"" +
                    ", \"userId\":"+userId+"}";
        }
    }
    /*
        delete a user by Id
        first check if the user exist
        then delete the user
     */
    @Override
    public String deleteUserById(long id){
        if (getUserById(id)==null){
            return "{\"status\": \"fail\"}";
        }else {
            try {
                userRepository.delete(id);
            }catch (Exception ex){
                return "{\"status\": \"fail\"}";
            }
            return "{\"status\": \"success\"}";
        }
    }
    /*
        find the user first then update the fields
     */
    @Override
    public String updateUser(long id,User user){
        User user1 = userRepository.findUserById(id);
        if (user1==null){
            return "{\"status\": \"fail\"}";
        }

        if(user.getName().trim()==null || user.getName()==""){
            return "{\"status\": \"fail\"}";
        }else if (user.getEmail().trim()==null || user.getEmail()==""){
            return "{\"status\": \"fail\"}";
        }else if (user.getPassword().trim()==null || user.getPassword()=="" ||user.getPassword().length()<10){
            return "{\"status\": \"fail\"}";
        }else if (user.getGender()==null) {
            return "{\"status\": \"fail\"}";
        }else {
            try {
                user1.setName(user.getName());
                user1.setEmail(user.getEmail());
                user1.setGender(user.getGender());
                user1.setBio(user.getBio());
                user1.setPhone(user.getPhone());
                user1.setCity(user.getCity());
                userRepository.save(user1);
            }catch (Exception e){
                return "{\"status\": \"fail\"}";
            }
            return "{\"status\": \"success\"}";
        }
    }

    public String updateUserProfilePhoto(long id,String photoURL){
        User user =userRepository.findUserById(id);
        user.setPhoto(photoURL);
        userRepository.save(user);
        return "success";
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User getUserByPhone(String phone) {
        return userRepository.findUserByPhone(phone);
    }


}
