package com.travel.dto;

import java.math.BigDecimal;

public class RelatedTourResponse {

    private Long id;
    private String title;
    private String slug;
    private Integer durationDays;
    private BigDecimal priceFrom;
    private String featuredImageUrl;

    public RelatedTourResponse() {
    }

    public RelatedTourResponse(
            Long id,
            String title,
            String slug,
            Integer durationDays,
            BigDecimal priceFrom,
            String featuredImageUrl
    ) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.durationDays = durationDays;
        this.priceFrom = priceFrom;
        this.featuredImageUrl = featuredImageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public Integer getDurationDays() {
        return durationDays;
    }

    public BigDecimal getPriceFrom() {
        return priceFrom;
    }

    public String getFeaturedImageUrl() {
        return featuredImageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setDurationDays(Integer durationDays) {
        this.durationDays = durationDays;
    }

    public void setPriceFrom(BigDecimal priceFrom) {
        this.priceFrom = priceFrom;
    }

    public void setFeaturedImageUrl(String featuredImageUrl) {
        this.featuredImageUrl = featuredImageUrl;
    }
}