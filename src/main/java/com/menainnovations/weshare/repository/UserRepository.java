package com.menainnovations.weshare.repository;

import com.menainnovations.weshare.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserById(long id);
    public User findUserByEmail(String email);
    public User findUserByPhone(String phone);





}
