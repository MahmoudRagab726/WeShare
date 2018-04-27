package com.menainnovations.weshare.services;

import com.menainnovations.weshare.model.Comment;
import com.menainnovations.weshare.model.Post;
import com.menainnovations.weshare.model.User;
import com.menainnovations.weshare.repository.CommentRepository;
import com.menainnovations.weshare.repository.PostRepository;
import com.menainnovations.weshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;


    @Override
    public String addComment(long userId , long postId , Comment comment) {
        Post post =postRepository.findPostById(postId);
        comment.setUserId(userId);
        comment.setPost(post);
        comment.setCommentDate(new Date());
        if(comment.getCommentContent()==null||comment.getCommentContent()==""){
            return "{\"status\": 0}";
        }else {
            commentRepository.save(comment);
            return "{\"status\": 1}";
        }
    }

    @Override
    public void deleteCommentById(long id) {
        commentRepository.delete(id);
    }

    @Override
    public String updateComment(long id, Comment comment) {
        try {
            Comment comment1 = commentRepository.findOne(id);
            comment1.setCommentContent(comment.getCommentContent());
            commentRepository.save(comment1);
        }catch (Exception e){
            return "{\"status\": 0}";
        }
        return "{\"status\": 1}";
    }
}
