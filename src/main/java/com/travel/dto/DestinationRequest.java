package com.travel.dto;

public class DestinationRequest {

    private Long countryId;
    private String region;

    private String nameEn;
    private String nameFr;

    private String slugEn;
    private String slugFr;

    private String shortDescriptionEn;
    private String shortDescriptionFr;

    private String contentEn;
    private String contentFr;

    private String bestTimeToVisitEn;
    private String bestTimeToVisitFr;

    private String thumbnailUrl;
    private String heroImageUrl;

    private Double latitude;
    private Double longitude;

    private Boolean isFeatured;
    private Boolean isActive;
    private Integer displayOrder;

    public DestinationRequest() {
    }

    public Long getCountryId() {
        return countryId;
    }

    public String getRegion() {
        return region;
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

    public String getShortDescriptionEn() {
        return shortDescriptionEn;
    }

    public String getShortDescriptionFr() {
        return shortDescriptionFr;
    }

    public String getContentEn() {
        return contentEn;
    }

    public String getContentFr() {
        return contentFr;
    }

    public String getBestTimeToVisitEn() {
        return bestTimeToVisitEn;
    }

    public String getBestTimeToVisitFr() {
        return bestTimeToVisitFr;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getHeroImageUrl() {
        return heroImageUrl;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public void setShortDescriptionEn(String shortDescriptionEn) {
        this.shortDescriptionEn = shortDescriptionEn;
    }

    public void setShortDescriptionFr(String shortDescriptionFr) {
        this.shortDescriptionFr = shortDescriptionFr;
    }

    public void setContentEn(String contentEn) {
        this.contentEn = contentEn;
    }

    public void setContentFr(String contentFr) {
        this.contentFr = contentFr;
    }

    public void setBestTimeToVisitEn(String bestTimeToVisitEn) {
        this.bestTimeToVisitEn = bestTimeToVisitEn;
    }

    public void setBestTimeToVisitFr(String bestTimeToVisitFr) {
        this.bestTimeToVisitFr = bestTimeToVisitFr;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setHeroImageUrl(String heroImageUrl) {
        this.heroImageUrl = heroImageUrl;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setIsFeatured(Boolean featured) {
        isFeatured = featured;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
}