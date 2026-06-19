package com.travel.dto;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDateTime;

public class TourResponse {

    private Long id;
    private String code;
    private Integer durationDays;
    private BigDecimal priceFrom;
    private String groupSize;

    private String title;
    private String slug;
    private String shortDescription;
    private String overview;
    private String itinerary;
    private String inclusion;
    private String exclusion;

    private String featuredImageUrl;

    private Boolean isFeatured;
    private Boolean isActive;
    private String status;
    private LocalDateTime createdAt;

    private List<String> imageUrls;
    private List<String> destinationNames;
    private List<String> styleNames;
    private List<String> collectionNames;
    private List<TourItineraryResponse> itineraryDays;

    public TourResponse() {
    }

    public TourResponse(
            Long id,
            String code,
            Integer durationDays,
            BigDecimal priceFrom,
            String groupSize,
            String title,
            String slug,
            String shortDescription,
            String overview,
            String itinerary,
            String inclusion,
            String exclusion,
            String featuredImageUrl,
            Boolean isFeatured,
            Boolean isActive,
            String status,
            LocalDateTime createdAt,
            List<String> imageUrls,
            List<String> destinationNames,
            List<String> styleNames,
            List<String> collectionNames,
            List<TourItineraryResponse> itineraryDays
    ) {
        this.id = id;
        this.code = code;
        this.durationDays = durationDays;
        this.priceFrom = priceFrom;
        this.groupSize = groupSize;
        this.title = title;
        this.slug = slug;
        this.shortDescription = shortDescription;
        this.overview = overview;
        this.itinerary = itinerary;
        this.inclusion = inclusion;
        this.exclusion = exclusion;
        this.featuredImageUrl = featuredImageUrl;
        this.isFeatured = isFeatured;
        this.isActive = isActive;
        this.status = status;
        this.createdAt = createdAt ;
        this.imageUrls = imageUrls;
        this.destinationNames = destinationNames;
        this.styleNames = styleNames;
        this.collectionNames = collectionNames;
        this.itineraryDays = itineraryDays; 
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Integer getDurationDays() {
        return durationDays;
    }

    public BigDecimal getPriceFrom() {
        return priceFrom;
    }

    public String getGroupSize() {
        return groupSize;
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getOverview() {
        return overview;
    }

    public String getItinerary() {
        return itinerary;
    }

    public String getInclusion() {
        return inclusion;
    }

    public String getExclusion() {
        return exclusion;
    }

    public String getFeaturedImageUrl() {
        return featuredImageUrl;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public List<String> getDestinationNames() {
        return destinationNames;
    }

    public List<String> getStyleNames() {
        return styleNames;
    }

    public List<String> getCollectionNames() {
        return collectionNames;
    }
    
    public List<TourItineraryResponse> getItineraryDays() {
        return itineraryDays;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDurationDays(Integer durationDays) {
        this.durationDays = durationDays;
    }

    public void setPriceFrom(BigDecimal priceFrom) {
        this.priceFrom = priceFrom;
    }

    public void setGroupSize(String groupSize) {
        this.groupSize = groupSize;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setItinerary(String itinerary) {
        this.itinerary = itinerary;
    }

    public void setInclusion(String inclusion) {
        this.inclusion = inclusion;
    }

    public void setExclusion(String exclusion) {
        this.exclusion = exclusion;
    }

    public void setFeaturedImageUrl(String featuredImageUrl) {
        this.featuredImageUrl = featuredImageUrl;
    }

    public void setIsFeatured(Boolean featured) {
        isFeatured = featured;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public void setDestinationNames(List<String> destinationNames) {
        this.destinationNames = destinationNames;
    }

    public void setStyleNames(List<String> styleNames) {
        this.styleNames = styleNames;
    }

    public void setCollectionNames(List<String> collectionNames) {
        this.collectionNames = collectionNames;
    }
    
    public void setItineraryDays(List<TourItineraryResponse> itineraryDays) {
        this.itineraryDays = itineraryDays;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}