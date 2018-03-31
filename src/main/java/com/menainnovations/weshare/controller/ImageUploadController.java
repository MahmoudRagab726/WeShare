package com.menainnovations.weshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping(value = "/api")
public class ImageUploadController {

    public static String ROOT = "uploads/images";
    int count=2;
    private final ResourceLoader resourceLoader;
    @Autowired
    public ImageUploadController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
/*
    @RequestMapping(method = RequestMethod.POST, value = "/files")
    public String handleFileUpload(@RequestParam("file") MultipartFile[] files,
                                   RedirectAttributes redirectAttributes) {
/*
for(MultipartFile file :files) {

    if (!file.isEmpty()) {
        try {
            System.out.println(">>>>>>>>>>Here images");
            BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
            File destination = new File(ROOT + "/images/"+count+"1.png"); // something like C:/Users/tom/Documents/nameBasedOnSomeId.png
            ImageIO.write(src, "png", destination);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + file.getOriginalFilename() + "!");
            System.out.println("Sucess");
            count++;
        } catch (IOException | RuntimeException e) {
            System.out.println("Error");
            redirectAttributes.addFlashAttribute("message", "Failed to upload " + file.getOriginalFilename() + " => " + e.getMessage());
        }
    } else {
        System.out.println("Images Empty");
        redirectAttributes.addFlashAttribute("message", "Failed to upload " + file.getOriginalFilename() + " because it was empty");
    }
}*//*

        return "redirect:/files";
    }
*/
    @RequestMapping(method = RequestMethod.GET, value = "uploads/images/post/{postId}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename,@PathVariable String postId) {

        try {
            return ResponseEntity.ok()
                    .body(resourceLoader.getResource("file:" + Paths.get(ROOT+ "/post/"+postId+"/", filename)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

}
