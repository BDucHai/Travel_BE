package com.travel.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "destinations")
public class Destination {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Một destination thuộc một country
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    // NORTH / CENTRAL / SOUTH
    @Column(length = 30)
    private String region;

    @Column(name = "name_en", nullable = false)
    private String nameEn;

    @Column(name = "name_fr", nullable = false)
    private String nameFr;

    @Column(name = "slug_en", nullable = false, unique = true)
    private String slugEn;

    @Column(name = "slug_fr", nullable = false, unique = true)
    private String slugFr;

    @Column(name = "short_description_en", length = 500)
    private String shortDescriptionEn;

    @Column(name = "short_description_fr", length = 500)
    private String shortDescriptionFr;

    @Column(name = "content_en", columnDefinition = "TEXT")
    private String contentEn;

    @Column(name = "content_fr", columnDefinition = "TEXT")
    private String contentFr;

    @Column(name = "best_time_to_visit_en")
    private String bestTimeToVisitEn;

    @Column(name = "best_time_to_visit_fr")
    private String bestTimeToVisitFr;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "hero_image_url")
    private String heroImageUrl;

    private Double latitude;

    private Double longitude;

    @Column(name = "is_featured")
    private Boolean isFeatured = false;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "display_order")
    private Integer displayOrder = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Destination() {
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if (this.isFeatured == null) {
            this.isFeatured = false;
        }

        if (this.isActive == null) {
            this.isActive = true;
        }

        if (this.displayOrder == null) {
            this.displayOrder = 0;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Country getCountry() {
        return country;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCountry(Country country) {
        this.country = country;
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

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
