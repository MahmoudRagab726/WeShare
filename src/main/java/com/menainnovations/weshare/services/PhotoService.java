package com.menainnovations.weshare.services;

import com.menainnovations.weshare.model.Photo;

import java.util.List;

public interface PhotoService {
    public void addPhoto(long postId ,Photo photo);
    public void deletePhotoById(long id);
    public List <Photo> getPhotoByPostId(long postId);



}
