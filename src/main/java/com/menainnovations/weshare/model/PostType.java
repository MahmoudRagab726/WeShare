package com.menainnovations.weshare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class PostType {
    private long id;
    private String postTypeName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column(name = "Post_Type_Name")
    public String getPostTypeName() {
        return postTypeName;
    }

    public void setPostTypeName(String postTypeName) {
        this.postTypeName = postTypeName;
    }

}
