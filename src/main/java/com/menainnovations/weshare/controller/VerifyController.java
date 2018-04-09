package com.menainnovations.weshare.controller;

import com.menainnovations.weshare.model.RegisterCodes;
import com.menainnovations.weshare.model.User;
import com.menainnovations.weshare.services.SendEmail;
import com.menainnovations.weshare.services.UserServiceImpl;
import com.menainnovations.weshare.services.VerifyAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/api")
public class VerifyController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    VerifyAccount verifyAccount;
    @RequestMapping(value = "/user/{userId}/sendVerification",method = RequestMethod.GET)
    public String sendVerificationCode(@PathVariable long userId){
        int result;
        try {
            User user = userService.getUserById(userId);
            if (user != null) {
                result = new SendEmail().sendEmail(user.getEmail());
                RegisterCodes code = new RegisterCodes();
                code.setUserId(userId);
                code.setDate(new Date());
                code.setCode(result);
                verifyAccount.addCode(code);

            }else {
                return "fail";
            }
        }catch (Exception e){
            return "fail";
        }
        return "success";
    }
    @RequestMapping(value = "user/{userId}/verify/{code}" , method = RequestMethod.GET)
    public String getCode(@PathVariable long userId,@PathVariable int code){
        RegisterCodes registerCodes =verifyAccount.getCode(userId);
        User user = userService.getUserById(userId);
        long x=registerCodes.getDate().getTime()-new Date().getTime();
        System.out.println(x);
        System.out.println(">>>"+registerCodes.getDate().getTime());
        if(code==registerCodes.getCode()&&user.getStatus()==0&&((new Date().getTime()-registerCodes.getDate().getTime())/(1000*60*60))<1){
            try {
                user.setStatus(1);
                userService.updateUser(userId, user);
            }catch (Exception e){
                return "fail";
            }
        }else {
            return "fail";
        }
        return "success";
    }
}
