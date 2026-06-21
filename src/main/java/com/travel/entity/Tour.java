package com.travel.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tours")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Column(name = "duration_days", nullable = false)
    private Integer durationDays;

    @Column(name = "price_from")
    private BigDecimal priceFrom;

    @Column(name = "group_size")
    private String groupSize;

    @Column(name = "title_en", nullable = false)
    private String titleEn;

    @Column(name = "title_fr", nullable = false)
    private String titleFr;

    @Column(name = "slug_en", nullable = false, unique = true)
    private String slugEn;

    @Column(name = "slug_fr", nullable = false, unique = true)
    private String slugFr;

    @Column(name = "short_description_en", length = 500)
    private String shortDescriptionEn;

    @Column(name = "short_description_fr", length = 500)
    private String shortDescriptionFr;

    @Column(name = "overview_en", columnDefinition = "TEXT")
    private String overviewEn;

    @Column(name = "overview_fr", columnDefinition = "TEXT")
    private String overviewFr;

    @Column(name = "itinerary_en", columnDefinition = "TEXT")
    private String itineraryEn;

    @Column(name = "itinerary_fr", columnDefinition = "TEXT")
    private String itineraryFr;

    @Column(name = "inclusion_en", columnDefinition = "TEXT")
    private String inclusionEn;

    @Column(name = "inclusion_fr", columnDefinition = "TEXT")
    private String inclusionFr;

    @Column(name = "exclusion_en", columnDefinition = "TEXT")
    private String exclusionEn;

    @Column(name = "exclusion_fr", columnDefinition = "TEXT")
    private String exclusionFr;

    @Column(name = "featured_image_url")
    private String featuredImageUrl;

    @Column(name = "is_featured")
    private Boolean isFeatured = false;

    @Column(name = "is_active")
    private Boolean isActive = true;

    private String status = "DRAFT";

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "tour_style_mapping",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_style_id")
    )
    private Set<TourStyle> styles = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "tour_collection_mapping",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_collection_id")
    )
    private Set<TourCollection> collections = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "tour_destination_mapping",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "destination_id")
    )
    private Set<Destination> destinations = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if (this.isFeatured == null) this.isFeatured = false;
        if (this.isActive == null) this.isActive = true;
        if (this.status == null) this.status = "DRAFT";
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Set<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(Set<Destination> destinations) {
        this.destinations = destinations;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Set<TourStyle> getStyles() {
        return styles;
    }

    public Set<TourCollection> getCollections() {
        return collections;
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

    public void setStyles(Set<TourStyle> styles) {
        this.styles = styles;
    }

    public void setCollections(Set<TourCollection> collections) {
        this.collections = collections;
    }
}