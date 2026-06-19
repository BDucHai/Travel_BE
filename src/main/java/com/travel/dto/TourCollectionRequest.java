package com.travel.dto;

public class TourCollectionRequest {

    private String type;

    private String nameEn;
    private String nameFr;

    private String slugEn;
    private String slugFr;

    private String descriptionEn;
    private String descriptionFr;

    private String thumbnailUrl;

    private Integer displayOrder;
    private Boolean isActive;

    public TourCollectionRequest() {
    }

    public String getType() {
        return type;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getNameFr() {
        return nameFr;
    }

    public String getSlugEn() {
        return slugEn;
    }

    public String getSlugFr() {
        return slugFr;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public String getDescriptionFr() {
        return descriptionFr;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public void setNameFr(String nameFr) {
        this.nameFr = nameFr;
    }

    public void setSlugEn(String slugEn) {
        this.slugEn = slugEn;
    }

    public void setSlugFr(String slugFr) {
        this.slugFr = slugFr;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public void setDescriptionFr(String descriptionFr) {
        this.descriptionFr = descriptionFr;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}