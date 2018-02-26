package com.menainnovations.weshare.services;

import com.menainnovations.weshare.model.User;

import java.util.List;

public interface UserService {
    public User getUserById(long id);
    public List<User> getAllUsers();
    public String addUser(User user);
    public String deleteUserById(long id);
    public String updateUser(long id,User user);
    public User getUserByEmail(String email);
}
