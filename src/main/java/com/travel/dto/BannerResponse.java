package com.travel.dto;

public class BannerResponse {

    private Long id;
    private String imageUrl;
    private String mobileImageUrl;
    private String linkUrl;
    private String position;
    private Integer displayOrder;
    private Boolean isActive;

    public BannerResponse() {
    }

    public BannerResponse(
            Long id,
            String imageUrl,
            String mobileImageUrl,
            String linkUrl,
            String position,
            Integer displayOrder,
            Boolean isActive
    ) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.mobileImageUrl = mobileImageUrl;
        this.linkUrl = linkUrl;
        this.position = position;
        this.displayOrder = displayOrder;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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