package com.travel.dto;

import java.time.LocalDateTime;
import java.util.List;

public class TestimonialResponse {

    private Long id;
    private String name;
    private String email;
    private String avatarUrl;
    private String country;
    private Integer rating;
    private String content;
    private LocalDateTime createdAt;
    private List<String> imageUrls;

    public TestimonialResponse() {
    }

    public TestimonialResponse(
            Long id,
            String name,
            String email,
            String avatarUrl,
            String country,
            Integer rating,
            String content,
            LocalDateTime createdAt,
            List<String> imageUrls
    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.country = country;
        this.rating = rating;
        this.content = content;
        this.createdAt = createdAt;
        this.imageUrls = imageUrls;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getCountry() {
        return country;
    }

    public Integer getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}