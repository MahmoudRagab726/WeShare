package com.menainnovations.weshare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class Post implements Serializable {
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
    private String postType;
    private List<Photo> photos;
    private List<Comment> comments;



    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "Id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column(name = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name = "Post_Content")
    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }
    @Column(name = "Post_Date")
    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
    @Column(name = "Case_Name")
    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }
    @Column(name = "Case_Contact")
    public String getCaseContact() {
        return caseContact;
    }

    public void setCaseContact(String caseContact) {
        this.caseContact = caseContact;
    }
    @Column(name = "Case_Address")
    public String getCaseAddress() {
        return caseAddress;
    }

    public void setCaseAddress(String caseAddress) {
        this.caseAddress = caseAddress;
    }

    @Column(name = "Case_City")
    public String getCaseCity() {
        return caseCity;
    }
    public void setCaseCity(String caseCity) {
        this.caseCity = caseCity;
    }

    @Column(name = "Case_Area")
    public String getCaseArea() {
        return caseArea;
    }

    public void setCaseArea(String caseArea) {
        this.caseArea = caseArea;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="User_Id")
    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "Post_Type")
    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    @OneToMany(mappedBy = "post")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "post")
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


}
