package com.menainnovations.weshare.controller;

import com.menainnovations.weshare.mobileReponse.ListPhotosResponse;
import com.menainnovations.weshare.model.Photo;
import com.menainnovations.weshare.services.PhotoServiceImpl;
import com.menainnovations.weshare.validator.UserTokenValidator;
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
    @Autowired
    UserTokenValidator userTokenValidator;
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
    public String addPhotoToPost(@RequestHeader("Access-Token") String token ,@PathVariable long postId , MultipartFile[] photos){
        if(token.trim()==""){
            return "{\"status\": 400}";
        }else if (userTokenValidator.validateToken(null,token)){
            if(photos.length>=1) {
                photoService.addPhoto(postId, photos);
                return "{\"status\": 1}";
            }else
                return "{\"status\": 0}";
        }else {
            return "{\"status\": 400}";
        }

    }

    @RequestMapping(value = "/user/{userId}/profilePhoto" , method = RequestMethod.POST)
    public String addProfilePhoto(@RequestHeader("Access-Token") String token ,@PathVariable long userId , MultipartFile photo){
        if(token.trim()==""){
            return "{\"status\": 400}";
        }else if (userTokenValidator.validateToken(userId,token)){
            return photoService.addProfilePhoto(userId,photo);
        }else {
            return "{\"status\": 400}";
        }

    }

    /**
     *
     * @param postId
     * @return
     */
    @RequestMapping(value = "/post/{postId}/photo",method = RequestMethod.GET)
    public ListPhotosResponse getPhotoForPost(@RequestHeader("Access-Token") String token , @PathVariable long postId){
        ListPhotosResponse response = new ListPhotosResponse();
        try {
            if (token.trim() == "") {
                response.setStatus(400);
                return response;
            } else if (userTokenValidator.validateToken(null, token)) {
                response.setStatus(1);
                response.setPosts(photoService.getPhotoByPostId(postId));
                return response;
            } else {
                response.setStatus(0);
                return response;
            }
        }catch (Exception e){
            response.setStatus(0);
            return response;
        }

    }

    /**
     *
     * @param id
     */
    @RequestMapping(value = "/photo/{id}",method = RequestMethod.DELETE)
    public String deletePhotoForPost(@RequestHeader("Access-Token") String token ,@PathVariable long id){
        if(token.trim()==""){
            return "{\"status\": 400}";
        }else if (userTokenValidator.validateToken(null,token)){
            photoService.deletePhotoById(id);
            return "{\"status\": 1}";
        }else {
            return "{\"status\": 400}";
        }

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
