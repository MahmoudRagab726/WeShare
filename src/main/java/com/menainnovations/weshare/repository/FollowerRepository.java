package com.menainnovations.weshare.repository;

import com.menainnovations.weshare.model.Follower;
import com.menainnovations.weshare.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface FollowerRepository extends CrudRepository<Follower,Long> {
    // get all followers for a user

    @Query(value = "select CONCAT(\"[\",\n" +
            "          GROUP_CONCAT(\n" +
            "                CONCAT(\"{id:\",id,\"\"),\n" +
            "               CONCAT(\",name:'\",name,\"'\"),\n" +
            "               CONCAT(\",photo:'\",photo,\"'}\")\n" +
            "          )\n" +
            "     ,\"]\")  from User u where u.id IN (select follower_id from follower f where f.user_id=:userId)",nativeQuery = true)
    public String findAllByUserId(@Param("userId") long userId);
    //get all following users
    @Query(value = "select CONCAT(\"[\",\n" +
            "          GROUP_CONCAT(\n" +
            "                CONCAT(\"{id:\",id,\"\"),\n" +
            "               CONCAT(\",name:'\",name,\"'\"),\n" +
            "               CONCAT(\",photo:'\",photo,\"'}\")\n" +
            "          )\n" +
            "     ,\"]\")  from User u where u.id IN (select user_id from follower f where f.follower_id=:followerId)",nativeQuery = true)
    public String findAllByFollowerId(@Param("followerId") long followerId);
    /*
    this to get if the is a following relation between two users
     */
    public Follower findAllByFollowerIdAndUserId(long followerId,long userId);
    public void deleteFollowerByFollowerIdAndUserId(long followerId,long userId);

}