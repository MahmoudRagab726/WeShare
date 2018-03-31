package com.menainnovations.weshare.services;

import com.menainnovations.weshare.model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {
    public String addPhoto(long postId , MultipartFile[] photos);
    public void deletePhotoById(long id);
    public List <Photo> getPhotoByPostId(long postId);



}
