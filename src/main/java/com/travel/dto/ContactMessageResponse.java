package com.travel.dto;

import java.time.LocalDateTime;

public class ContactMessageResponse {

    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String subject;
    private String message;
    private String status;
    private LocalDateTime createdAt;
    private Long blogId;
    private String blogTitle;

    
    public ContactMessageResponse() {
    }

    public ContactMessageResponse(
            Long id,
            String fullName,
            String email,
            String phoneNumber,
            String subject,
            String message,
            String status,
            LocalDateTime createdAt,
            Long blogId,
            String blogTitle
    ) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.subject = subject;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
        this.blogId = blogId;
        this.blogTitle = blogTitle;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public Long getBlogId() {
        return blogId;
    }
    
    public String getBlogTitle() {
        return blogTitle;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
    
    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }
    
}