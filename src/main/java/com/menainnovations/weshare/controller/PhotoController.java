package com.menainnovations.weshare.controller;

import com.menainnovations.weshare.model.Photo;
import com.menainnovations.weshare.services.PhotoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class PhotoController {
    @Autowired
    PhotoServiceImpl photoService;

    @RequestMapping(value = "/post/{postId}/photo" , method = RequestMethod.POST)
    public void addPhotoToPost(@RequestBody Photo photo, @PathVariable long postId){
        photoService.addPhoto(postId,photo);
    }

    @RequestMapping(value = "/post/{postId}/photo",method = RequestMethod.GET)
    public List<Photo> getPhotoForPost(@PathVariable long postId){
        return photoService.getPhotoByPostId(postId);
    }
    @RequestMapping(value = "/photo/{id}",method = RequestMethod.DELETE)
    public void deletePhotoForPost(@PathVariable long id){
        photoService.deletePhotoById(id);
    }

}
