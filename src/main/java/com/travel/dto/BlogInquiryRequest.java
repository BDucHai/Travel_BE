package com.travel.dto;

public class BlogInquiryRequest {

    private Long blogId;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String message;

    public BlogInquiryRequest() {
    }

    public Long getBlogId() {
        return blogId;
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

    public String getMessage() {
        return message;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
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

    public void setMessage(String message) {
        this.message = message;
    }
}