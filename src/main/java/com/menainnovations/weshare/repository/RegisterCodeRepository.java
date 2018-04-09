package com.menainnovations.weshare.repository;

import com.menainnovations.weshare.model.RegisterCodes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterCodeRepository extends CrudRepository<RegisterCodes , Long>{
    public RegisterCodes findRegisterCodesByUserId(long userId);

}
