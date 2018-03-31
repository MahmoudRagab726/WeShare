package com.menainnovations.weshare.services;

import com.menainnovations.weshare.model.Post;
import com.menainnovations.weshare.model.User;
import com.menainnovations.weshare.repository.PostRepository;
import com.menainnovations.weshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Post getPostById(long id) {
        return postRepository.findPostById(id);
    }

    @Override
    public List<Post> getPostByArea(String area) {
        return postRepository.findPostByCaseAreaOrderByPostDateDesc(area);
    }

    @Override
    public List<Post> getPostByCity(String city) {
        return postRepository.findPostByCaseCityOrderByPostDateDesc(city);
    }

    @Override
    public List<Post> getPostByAreaAndCity(String area, String city) {
        return postRepository.findPostByCaseAreaAndCaseCityOrderByPostDateDesc(area,city);
    }

    @Override
    public List<Post> getAllPostsByUserId(long userId) {
        return (List<Post>) postRepository.findAllByUserIdOrderByPostDateDesc(userId);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByPostDateDesc();
    }

    @Override
    public String addPost(long userId, Post post) {
        long postId=0;
        User user = userRepository.findUserById(userId);
        if (user==null){
            return "fail";
        }else if (post.getTitle()==null || post.getTitle().trim()==""){
            return "fail";
        }else  if (post.getPostContent()==null||post.getPostContent().trim()==""){
            return "fail";
        }else {
            try {
                post.setUser(user);
                post.setPostDate(new Date());
                postId= postRepository.save(post).getId();
                System.out.println(postId);
            }catch (Exception e){
                return "fail";
            }
            return "{\"status\": \"success\"" +
                    ", \"postId\": "+postId+"}";
        }

    }
    @Override
    public void deletePostById(long id) {
        postRepository.delete(id);
    }

    @Override
    public String updatePost(long id , Post post) {
        try {
            postRepository.updatePost(id, post.getTitle(), post.getPostContent(), post.getCaseName(), post.getCaseContact(), post.getCaseAddress(), post.getCaseCity(), post.getCaseArea());
        }catch (Exception e){
            return "fail";
        }
            return "success";
    }
    public List<Post> getPostsByFollower(long id){
        return postRepository.findAllByUserIdForFollowing(id);
    }
}