package com.menainnovations.weshare.validator;

import com.menainnovations.weshare.model.User;
import com.menainnovations.weshare.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenValidator {
    @Autowired
    private UserServiceImpl userService;
    public boolean validateToken(Long userId ,String token){
        try {
            String[] subToken = token.split("basic");
            String id = subToken[0];
            String phone = subToken[1];
            if (id.equals(userId + "")|| userId==null) {
                userId=Long.parseLong(id);
                User user = userService.getUserById(userId);
                if (user.getPhone().equals(phone)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

}
