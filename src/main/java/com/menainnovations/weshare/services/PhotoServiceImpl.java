package com.menainnovations.weshare.services;

import com.menainnovations.weshare.model.Photo;
import com.menainnovations.weshare.model.Post;
import com.menainnovations.weshare.repository.PhotoRepository;
import com.menainnovations.weshare.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    public static String ROOT = "uploads/images/post";
    public static String ROOT2 = "uploads/images/profile";
    long count = 0;
    private final ResourceLoader resourceLoader;

    @Autowired
    public PhotoServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Autowired
    PhotoRepository photoRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserService userService;


    @Override
    public String addPhoto(long postId, MultipartFile[] photos) {

        File directory= new File(ROOT + "/"+postId);
        if (!directory.exists()){
            directory.mkdir();
        }
        for (MultipartFile photo : photos) {
            String photoName=new Date().toString().replace(" ","").replace(":","_");
            if (!photo.isEmpty()) {
                try {
                    BufferedImage src = ImageIO.read(new ByteArrayInputStream(photo.getBytes()));
                    File destination = new File(ROOT + "/"+postId+"/" +photoName+count + "1.jpg");
                    if (!destination.exists()){
                        destination.mkdir();
                    }
                    ImageIO.write(src, "jpg", destination);
                    count++;
                    /*
                        create a new Photo Obj to store info in DB photo table
                     */
                    Photo photo1=new Photo();
                    photo1.setUrl(destination.toString());
                    photo1.setPost(postRepository.findPostById(postId));
                    photoRepository.save(photo1);
                } catch (IOException | RuntimeException e) {
                    return "fail";
                }
            } else {
                return "fail";
            }
        }
        return "success";
    }
    public String addProfilePhoto(long userId ,MultipartFile photo) {
        File directory = new File(ROOT2 + "/" + userId);
        if (!directory.exists()) {
            directory.mkdir();
        }
        String photoName = new Date().toString().replace(" ", "").replace(":", "_");
        if (!photo.isEmpty()) {
            try {
                BufferedImage src = ImageIO.read(new ByteArrayInputStream(photo.getBytes()));
                File destination = new File(ROOT2 + "/" + userId + "/" + photoName + "22.jpg");
                if (!destination.exists()) {
                    destination.mkdir();
                }
                ImageIO.write(src, "jpg", destination);
                    /*
                        update user object to set profile pic
                     */
                    userService.updateUserProfilePhoto(userId,destination.toString().replace("\\","/"));

            } catch (IOException | RuntimeException e) {
                return "{\"status\": 0}";
            }
        }
        return "{\"status\": 1}";
    }


    @Override
    public String deletePhotoById(long id) {
        try {
            Photo photo =photoRepository.findOne(id);
            File file=new File(photo.getUrl());
            if(file.delete()) {
                photoRepository.delete(id);
            }
        }catch (Exception e){
            return "{\"status\": 0}";
        }
        return "{\"status\": 1}";
    }

    @Override
    public List<Photo> getPhotoByPostId(long postId) {
        return photoRepository.findAllByPostId(postId);
    }

}
