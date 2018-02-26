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
        if(user.getName().trim()==null || user.getName()==""){
            return "fail";
        }else if (user.getEmail().trim()==null || user.getEmail()==""){
            return "fail";
        }else if (user.getPassword().trim()==null || user.getPassword()=="" ||user.getPassword().length()<10){
            return "fail";
        }else if (user.getGender()==null){
            return "fail";
        }else {
            try {
                userRepository.save(user);
            }catch (Exception ex){
                return "fail";
            }
            return "success";
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
            return"status : User not found";
        }else {
            try {
                userRepository.delete(id);
            }catch (Exception ex){
                return "fail";
            }
            return "success";
        }
    }
    /*
        find the user first then update the fields
     */
    @Override
    public String updateUser(long id,User user){
        User user1 = userRepository.findUserById(id);
        if (user1==null){
            return "User not found";
        }

        if(user.getName().trim()==null || user.getName()==""){
            return "fail";
        }else if (user.getEmail().trim()==null || user.getEmail()==""){
            return "fail";
        }else if (user.getPassword().trim()==null || user.getPassword()=="" ||user.getPassword().length()<10){
            return "fail";
        }else if (user.getGender()==null) {
            return "fail";
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
                return "fail";
            }
            return "success";
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


}
