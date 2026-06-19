package com.travel.dto;

import java.math.BigDecimal;

public class AdminBlogRelatedTourResponse {

    private Long id;
    private String titleEn;
    private String titleFr;
    private String slugEn;
    private String slugFr;
    private Integer durationDays;
    private BigDecimal priceFrom;
    private String featuredImageUrl;

    public AdminBlogRelatedTourResponse() {
    }

    public AdminBlogRelatedTourResponse(
            Long id,
            String titleEn,
            String titleFr,
            String slugEn,
            String slugFr,
            Integer durationDays,
            BigDecimal priceFrom,
            String featuredImageUrl
    ) {
        this.id = id;
        this.titleEn = titleEn;
        this.titleFr = titleFr;
        this.slugEn = slugEn;
        this.slugFr = slugFr;
        this.durationDays = durationDays;
        this.priceFrom = priceFrom;
        this.featuredImageUrl = featuredImageUrl;
    }

    public Long getId() {
        return id;
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