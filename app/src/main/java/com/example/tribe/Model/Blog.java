package com.example.tribe.Model;

public class Blog {

    String blogTitle, blogImage, blogDescription, blogDate, blogReadTime;

    public Blog(String blogTitle, String blogImage, String blogDescription, String blogDate, String blogReadTime) {
        this.blogTitle = blogTitle;
        this.blogImage = blogImage;
        this.blogDescription = blogDescription;
        this.blogDate = blogDate;
        this.blogReadTime = blogReadTime;
    }

    public Blog() {
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogImage() {
        return blogImage;
    }

    public void setBlogImage(String blogImage) {
        this.blogImage = blogImage;
    }

    public String getBlogDescription() {
        return blogDescription;
    }

    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }

    public String getBlogDate() {
        return blogDate;
    }

    public void setBlogDate(String blogDate) {
        this.blogDate = blogDate;
    }

    public String getBlogReadTime() {
        return blogReadTime;
    }

    public void setBlogReadTime(String blogReadTime) {
        this.blogReadTime = blogReadTime;
    }
}
