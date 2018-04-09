package com.menainnovations.weshare.responses;

import com.menainnovations.weshare.model.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class PostResponse {
    private long id;
    private String title;
    private String postContent;
    private Date postDate;
    private String caseName;
    private String caseContact;
    private String caseAddress;
    private String caseCity;
    private String caseArea;
    private User user;
    private List<Photo> photos;
    private List<CommentResponse> comments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseContact() {
        return caseContact;
    }

    public void setCaseContact(String caseContact) {
        this.caseContact = caseContact;
    }

    public String getCaseAddress() {
        return caseAddress;
    }

    public void setCaseAddress(String caseAddress) {
        this.caseAddress = caseAddress;
    }

    public String getCaseCity() {
        return caseCity;
    }

    public void setCaseCity(String caseCity) {
        this.caseCity = caseCity;
    }

    public String getCaseArea() {
        return caseArea;
    }

    public void setCaseArea(String caseArea) {
        this.caseArea = caseArea;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }
}
