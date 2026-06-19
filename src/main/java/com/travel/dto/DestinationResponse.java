package com.travel.dto;

public class DestinationResponse {

    private Long id;
    private Long countryId;
    private String countryName;
    private String region;

    private String name;
    private String slug;
    private String shortDescription;
    private String content;
    private String bestTimeToVisit;

    private String thumbnailUrl;
    private String heroImageUrl;

    private Double latitude;
    private Double longitude;

    private Boolean isFeatured;
    private Boolean isActive;
    private Integer displayOrder;

    public DestinationResponse() {
    }

    public DestinationResponse(
            Long id,
            Long countryId,
            String countryName,
            String region,
            String name,
            String slug,
            String shortDescription,
            String content,
            String bestTimeToVisit,
            String thumbnailUrl,
            String heroImageUrl,
            Double latitude,
            Double longitude,
            Boolean isFeatured,
            Boolean isActive,
            Integer displayOrder
    ) {
        this.id = id;
        this.countryId = countryId;
        this.countryName = countryName;
        this.region = region;
        this.name = name;
        this.slug = slug;
        this.shortDescription = shortDescription;
        this.content = content;
        this.bestTimeToVisit = bestTimeToVisit;
        this.thumbnailUrl = thumbnailUrl;
        this.heroImageUrl = heroImageUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isFeatured = isFeatured;
        this.isActive = isActive;
        this.displayOrder = displayOrder;
    }

    public Long getId() {
        return id;
    }

    public Long getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getRegion() {
        return region;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getContent() {
        return content;
    }

    public String getBestTimeToVisit() {
        return bestTimeToVisit;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setBestTimeToVisit(String bestTimeToVisit) {
        this.bestTimeToVisit = bestTimeToVisit;
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