package com.menainnovations.weshare.services;

import com.menainnovations.weshare.model.Photo;
import com.menainnovations.weshare.model.Post;
import com.menainnovations.weshare.repository.PhotoRepository;
import com.menainnovations.weshare.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService{

    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    PostRepository postRepository;
    @Override
    public void addPhoto(long postId,Photo photo) {
        Post post=postRepository.findPostById(postId);
        photo.setPost(post);
        photoRepository.save(photo);
    }

    @Override
    public void deletePhotoById(long id) {
        photoRepository.delete(id);
    }

    @Override
    public List<Photo> getPhotoByPostId(long postId) {
       return photoRepository.findAllByPostId(postId);
    }

}
