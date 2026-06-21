package com.travel.dto;

import java.math.BigDecimal;
import java.util.List;

public class TourRequest {

    private String code;
    private Integer durationDays;
    private BigDecimal priceFrom;
    private String groupSize;

    private String titleEn;
    private String titleFr;

    private String slugEn;
    private String slugFr;

    private String shortDescriptionEn;
    private String shortDescriptionFr;

    private String overviewEn;
    private String overviewFr;

    private String itineraryEn;
    private String itineraryFr;

    private String inclusionEn;
    private String inclusionFr;

    private String exclusionEn;
    private String exclusionFr;

    private String featuredImageUrl;
    private List<String> imageUrls; 

    private Boolean isFeatured;
    private Boolean isActive;
    private String status;

    private List<Long> styleIds;
    private List<Long> collectionIds;
    private List<TourItineraryRequest> itineraryDays;

    public TourRequest() {
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

    public String getTitleEn() {
        return titleEn;
    }

    public String getTitleFr() {
        return titleFr;
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

    public String getOverviewEn() {
        return overviewEn;
    }

    public String getOverviewFr() {
        return overviewFr;
    }

    public String getItineraryEn() {
        return itineraryEn;
    }

    public String getItineraryFr() {
        return itineraryFr;
    }

    public String getInclusionEn() {
        return inclusionEn;
    }

    public String getInclusionFr() {
        return inclusionFr;
    }

    public String getExclusionEn() {
        return exclusionEn;
    }

    public String getExclusionFr() {
        return exclusionFr;
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

    public List<Long> getStyleIds() {
        return styleIds;
    }

    public List<Long> getCollectionIds() {
        return collectionIds;
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

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public void setTitleFr(String titleFr) {
        this.titleFr = titleFr;
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

    public void setOverviewEn(String overviewEn) {
        this.overviewEn = overviewEn;
    }

    public void setOverviewFr(String overviewFr) {
        this.overviewFr = overviewFr;
    }

    public void setItineraryEn(String itineraryEn) {
        this.itineraryEn = itineraryEn;
    }

    public void setItineraryFr(String itineraryFr) {
        this.itineraryFr = itineraryFr;
    }

    public void setInclusionEn(String inclusionEn) {
        this.inclusionEn = inclusionEn;
    }

    public void setInclusionFr(String inclusionFr) {
        this.inclusionFr = inclusionFr;
    }

    public void setExclusionEn(String exclusionEn) {
        this.exclusionEn = exclusionEn;
    }

    public void setExclusionFr(String exclusionFr) {
        this.exclusionFr = exclusionFr;
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

    public void setStyleIds(List<Long> styleIds) {
        this.styleIds = styleIds;
    }

    public void setCollectionIds(List<Long> collectionIds) {
        this.collectionIds = collectionIds;
    }
    public List<TourItineraryRequest> getItineraryDays() {
        return itineraryDays;
    }

    public void setItineraryDays(List<TourItineraryRequest> itineraryDays) {
        this.itineraryDays = itineraryDays;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}