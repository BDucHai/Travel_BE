package com.travel.dto;

public class BannerRequest {

    private String imageUrl;
    private String mobileImageUrl;
    private String linkUrl;
    private String position;
    private Integer displayOrder;
    private Boolean isActive;

    public BannerRequest() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getMobileImageUrl() {
        return mobileImageUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public String getPosition() {
        return position;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setMobileImageUrl(String mobileImageUrl) {
        this.mobileImageUrl = mobileImageUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}