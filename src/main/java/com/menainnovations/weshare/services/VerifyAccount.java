package com.menainnovations.weshare.services;

import com.menainnovations.weshare.model.RegisterCodes;
import com.menainnovations.weshare.repository.RegisterCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyAccount {
    @Autowired
    RegisterCodeRepository registerCodeRepository;
    public void addCode(RegisterCodes registerCodes){
        registerCodeRepository.save(registerCodes);
    }
    public RegisterCodes getCode(long userId){
        return registerCodeRepository.findRegisterCodesByUserId(userId);
    }
}
