package com.travel.dto;

public class TestimonialImageRequest {

    private String imageUrl;
    private Integer displayOrder;

    public TestimonialImageRequest() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
}