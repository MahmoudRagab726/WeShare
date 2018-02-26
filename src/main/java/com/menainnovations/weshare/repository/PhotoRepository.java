package com.menainnovations.weshare.repository;

import com.menainnovations.weshare.model.Photo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends CrudRepository<Photo,Long>{

    public List<Photo> findAllByPostId(long postId);


}
