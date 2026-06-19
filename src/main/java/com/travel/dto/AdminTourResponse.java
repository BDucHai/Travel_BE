package com.travel.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class AdminTourResponse {

    private Long id;
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
    private Boolean isFeatured;
    private Boolean isActive;
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<String> imageUrls;

    private List<Long> destinationIds;
    private List<String> destinationNamesEn;
    private List<String> destinationNamesFr;

    private List<Long> styleIds;
    private List<String> styleNamesEn;
    private List<String> styleNamesFr;

    private List<Long> collectionIds;
    private List<String> collectionNamesEn;
    private List<String> collectionNamesFr;

    private List<AdminTourItineraryResponse> itineraryDays;

    public AdminTourResponse() {
    }

    public AdminTourResponse(
            Long id,
            String code,
            Integer durationDays,
            BigDecimal priceFrom,
            String groupSize,
            String titleEn,
            String titleFr,
            String slugEn,
            String slugFr,
            String shortDescriptionEn,
            String shortDescriptionFr,
            String overviewEn,
            String overviewFr,
            String itineraryEn,
            String itineraryFr,
            String inclusionEn,
            String inclusionFr,
            String exclusionEn,
            String exclusionFr,
            String featuredImageUrl,
            Boolean isFeatured,
            Boolean isActive,
            String status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            List<String> imageUrls,
            List<Long> destinationIds,
            List<String> destinationNamesEn,
            List<String> destinationNamesFr,
            List<Long> styleIds,
            List<String> styleNamesEn,
            List<String> styleNamesFr,
            List<Long> collectionIds,
            List<String> collectionNamesEn,
            List<String> collectionNamesFr,
            List<AdminTourItineraryResponse> itineraryDays
    ) {
        this.id = id;
        this.code = code;
        this.durationDays = durationDays;
        this.priceFrom = priceFrom;
        this.groupSize = groupSize;
        this.titleEn = titleEn;
        this.titleFr = titleFr;
        this.slugEn = slugEn;
        this.slugFr = slugFr;
        this.shortDescriptionEn = shortDescriptionEn;
        this.shortDescriptionFr = shortDescriptionFr;
        this.overviewEn = overviewEn;
        this.overviewFr = overviewFr;
        this.itineraryEn = itineraryEn;
        this.itineraryFr = itineraryFr;
        this.inclusionEn = inclusionEn;
        this.inclusionFr = inclusionFr;
        this.exclusionEn = exclusionEn;
        this.exclusionFr = exclusionFr;
        this.featuredImageUrl = featuredImageUrl;
        this.isFeatured = isFeatured;
        this.isActive = isActive;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.imageUrls = imageUrls;
        this.destinationIds = destinationIds;
        this.destinationNamesEn = destinationNamesEn;
        this.destinationNamesFr = destinationNamesFr;
        this.styleIds = styleIds;
        this.styleNamesEn = styleNamesEn;
        this.styleNamesFr = styleNamesFr;
        this.collectionIds = collectionIds;
        this.collectionNamesEn = collectionNamesEn;
        this.collectionNamesFr = collectionNamesFr;
        this.itineraryDays = itineraryDays;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getDurationDays() {
		return durationDays;
	}

	public void setDurationDays(Integer durationDays) {
		this.durationDays = durationDays;
	}

	public BigDecimal getPriceFrom() {
		return priceFrom;
	}

	public void setPriceFrom(BigDecimal priceFrom) {
		this.priceFrom = priceFrom;
	}

	public String getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(String groupSize) {
		this.groupSize = groupSize;
	}

	public String getTitleEn() {
		return titleEn;
	}

	public void setTitleEn(String titleEn) {
		this.titleEn = titleEn;
	}

	public String getTitleFr() {
		return titleFr;
	}

	public void setTitleFr(String titleFr) {
		this.titleFr = titleFr;
	}

	public String getSlugEn() {
		return slugEn;
	}

	public void setSlugEn(String slugEn) {
		this.slugEn = slugEn;
	}

	public String getSlugFr() {
		return slugFr;
	}

	public void setSlugFr(String slugFr) {
		this.slugFr = slugFr;
	}

	public String getShortDescriptionEn() {
		return shortDescriptionEn;
	}

	public void setShortDescriptionEn(String shortDescriptionEn) {
		this.shortDescriptionEn = shortDescriptionEn;
	}

	public String getShortDescriptionFr() {
		return shortDescriptionFr;
	}

	public void setShortDescriptionFr(String shortDescriptionFr) {
		this.shortDescriptionFr = shortDescriptionFr;
	}

	public String getOverviewEn() {
		return overviewEn;
	}

	public void setOverviewEn(String overviewEn) {
		this.overviewEn = overviewEn;
	}

	public String getOverviewFr() {
		return overviewFr;
	}

	public void setOverviewFr(String overviewFr) {
		this.overviewFr = overviewFr;
	}

	public String getItineraryEn() {
		return itineraryEn;
	}

	public void setItineraryEn(String itineraryEn) {
		this.itineraryEn = itineraryEn;
	}

	public String getItineraryFr() {
		return itineraryFr;
	}

	public void setItineraryFr(String itineraryFr) {
		this.itineraryFr = itineraryFr;
	}

	public String getInclusionEn() {
		return inclusionEn;
	}

	public void setInclusionEn(String inclusionEn) {
		this.inclusionEn = inclusionEn;
	}

	public String getInclusionFr() {
		return inclusionFr;
	}

	public void setInclusionFr(String inclusionFr) {
		this.inclusionFr = inclusionFr;
	}

	public String getExclusionEn() {
		return exclusionEn;
	}

	public void setExclusionEn(String exclusionEn) {
		this.exclusionEn = exclusionEn;
	}

	public String getExclusionFr() {
		return exclusionFr;
	}

	public void setExclusionFr(String exclusionFr) {
		this.exclusionFr = exclusionFr;
	}

	public String getFeaturedImageUrl() {
		return featuredImageUrl;
	}

	public void setFeaturedImageUrl(String featuredImageUrl) {
		this.featuredImageUrl = featuredImageUrl;
	}

	public Boolean getIsFeatured() {
		return isFeatured;
	}

	public void setIsFeatured(Boolean isFeatured) {
		this.isFeatured = isFeatured;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public List<Long> getDestinationIds() {
		return destinationIds;
	}

	public void setDestinationIds(List<Long> destinationIds) {
		this.destinationIds = destinationIds;
	}

	public List<String> getDestinationNamesEn() {
		return destinationNamesEn;
	}

	public void setDestinationNamesEn(List<String> destinationNamesEn) {
		this.destinationNamesEn = destinationNamesEn;
	}

	public List<String> getDestinationNamesFr() {
		return destinationNamesFr;
	}

	public void setDestinationNamesFr(List<String> destinationNamesFr) {
		this.destinationNamesFr = destinationNamesFr;
	}

	public List<Long> getStyleIds() {
		return styleIds;
	}

	public void setStyleIds(List<Long> styleIds) {
		this.styleIds = styleIds;
	}

	public List<String> getStyleNamesEn() {
		return styleNamesEn;
	}

	public void setStyleNamesEn(List<String> styleNamesEn) {
		this.styleNamesEn = styleNamesEn;
	}

	public List<String> getStyleNamesFr() {
		return styleNamesFr;
	}

	public void setStyleNamesFr(List<String> styleNamesFr) {
		this.styleNamesFr = styleNamesFr;
	}

	public List<Long> getCollectionIds() {
		return collectionIds;
	}

	public void setCollectionIds(List<Long> collectionIds) {
		this.collectionIds = collectionIds;
	}

	public List<String> getCollectionNamesEn() {
		return collectionNamesEn;
	}

	public void setCollectionNamesEn(List<String> collectionNamesEn) {
		this.collectionNamesEn = collectionNamesEn;
	}

	public List<String> getCollectionNamesFr() {
		return collectionNamesFr;
	}

	public void setCollectionNamesFr(List<String> collectionNamesFr) {
		this.collectionNamesFr = collectionNamesFr;
	}

	public List<AdminTourItineraryResponse> getItineraryDays() {
		return itineraryDays;
	}

	public void setItineraryDays(List<AdminTourItineraryResponse> itineraryDays) {
		this.itineraryDays = itineraryDays;
	}
    

    // Generate Getter/Setter bằng Eclipse:
    // Right click -> Source -> Generate Getters and Setters -> Select All
}