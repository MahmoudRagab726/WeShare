package com.menainnovations.weshare.controller;

import com.menainnovations.weshare.model.Photo;
import com.menainnovations.weshare.services.PhotoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class PhotoController {
    @Autowired
    PhotoServiceImpl photoService;
    public static String ROOT = "uploads/images";
    int count=2;
    private final ResourceLoader resourceLoader;
    @Autowired
    public PhotoController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

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

    @RequestMapping(value = "/user/{userId}/profilePhoto" , method = RequestMethod.POST)
    public String addProfilePhoto(@PathVariable long userId , MultipartFile photo){
            return photoService.addProfilePhoto(userId,photo);
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

    @RequestMapping(method = RequestMethod.GET, value = "uploads/images/post/{postId}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename, @PathVariable String postId) {

        try {
            return ResponseEntity.ok()
                    .body(resourceLoader.getResource("file:" + Paths.get(ROOT+ "/post/"+postId+"/", filename)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "uploads/images/profile/{userId}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getProfilePhoto(@PathVariable String filename,@PathVariable String userId) {

        try {
            return ResponseEntity.ok()
                    .body(resourceLoader.getResource("file:" + Paths.get(ROOT+ "/profile/"+userId+"/", filename)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

}
