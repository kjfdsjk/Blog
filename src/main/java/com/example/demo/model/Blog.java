package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content ;
    private String title;
    private String status;
    private double likeCount;
    @ManyToOne
    private User user;

    public Blog() {
    }

    public Blog(Long id, String content, String title, String status, double likeCount, User user) {
        this.id = id;
        this.content = content;
        this.title = title;
        this.status = status;
        this.likeCount = likeCount;
        this.user = user;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(double likeCount) {
        this.likeCount = likeCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

