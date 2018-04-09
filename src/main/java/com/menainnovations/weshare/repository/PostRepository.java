package com.menainnovations.weshare.repository;

import com.menainnovations.weshare.model.Post;
import com.menainnovations.weshare.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository< Post, Long> {
    public Post findPostById(long id);

    public List<Post> findAllByUserIdOrderByPostDateDesc(long userId);

    public Post findByUserId(long userId);

    @Query(value = "select * from Post p where p.user_id IN (select user_id from follower where follower_id=:id) ORDER BY post_date", nativeQuery = true)
    public List<Post> findAllByUserIdForFollowing(@Param("id") long id);

    @Transactional
    @Modifying
    @Query(value = "update Post set title =:title , postContent=:postContent , caseName = :caseName , caseContact=:caseContact , caseAddress=:caseAddress" +
            ", caseCity=:caseCity , caseArea = :caseArea where id=:id")
    public void updatePost(@Param("id")long id,@Param("title") String title,@Param("postContent") String postContent , @Param("caseName") String caseName ,@Param("caseContact") String caseContact
                           ,@Param("caseAddress") String caseAddress ,@Param("caseCity") long caseCity ,@Param("caseArea") long caseArea);

    public List<Post> findPostByCaseAreaOrderByPostDateDesc(String caseArea);

    public List<Post> findPostByCaseCityOrderByPostDateDesc(String caseCity);

    public List<Post> findPostByCaseAreaAndCaseCityOrderByPostDateDesc(String caseArea,String caseCity);

    public List<Post> findAllByOrderByPostDateDesc();
}
