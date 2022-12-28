package com.example.tribe.Model;

public class Post {

    private String postid;
    private String postimage;
    private String description;
    private String publisher;
    private String likesCount;

    public Post() {
    }

    public Post(String postid, String postimage, String description, String publisher, String likesCount) {
        this.postid = postid;
        this.postimage = postimage;
        this.description = description;
        this.publisher = publisher;
        this.likesCount = likesCount;
    }

    public String getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(String likesCount) {
        this.likesCount = likesCount;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getPostimage() {
        return postimage;
    }

    public void setPostimage(String postimage) {
        this.postimage = postimage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
