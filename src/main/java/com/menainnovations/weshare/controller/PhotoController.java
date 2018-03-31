package com.menainnovations.weshare.controller;

import com.menainnovations.weshare.model.Photo;
import com.menainnovations.weshare.services.PhotoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class PhotoController {
    @Autowired
    PhotoServiceImpl photoService;

    /**
     *
     * @param postId
     * @param photos
     * @return success or fail
     */

    @RequestMapping(value = "/post/{postId}/photos" , method = RequestMethod.POST)
    public String addPhotoToPost(@PathVariable long postId , MultipartFile[] photos){
        if(photos.length>=1) {
            photoService.addPhoto(postId, photos);
            return "success";
        }else
            return "fail";
    }

    /**
     *
     * @param postId
     * @return
     */
    @RequestMapping(value = "/post/{postId}/photo",method = RequestMethod.GET)
    public List<Photo> getPhotoForPost(@PathVariable long postId){
        return photoService.getPhotoByPostId(postId);
    }

    /**
     *
     * @param id
     */
    @RequestMapping(value = "/photo/{id}",method = RequestMethod.DELETE)
    public void deletePhotoForPost(@PathVariable long id){
        photoService.deletePhotoById(id);
    }

}
