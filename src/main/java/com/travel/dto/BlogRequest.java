package com.travel.dto;

import java.time.LocalDateTime;

public class BlogRequest {

    private String authorName;

    private String titleEn;
    private String titleFr;

    private String slugEn;
    private String slugFr;

    private String excerptEn;
    private String excerptFr;

    private String contentEn;
    private String contentFr;

    private String thumbnailUrl;
    private String heroImageUrl;

    private Boolean isFeatured;
    private Boolean isMostRead;

    private String status;
    private LocalDateTime publishedAt;

    private Set<Long> relatedTourIds;


    public Set<Long> getRelatedTourIds() {
        return relatedTourIds;
    }

    public void setRelatedTourIds(Set<Long> relatedTourIds) {
        this.relatedTourIds = relatedTourIds;
    }

    public BlogRequest() {
    }

    public String getAuthorName() {
        return authorName;
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

    public String getExcerptEn() {
        return excerptEn;
    }

    public String getExcerptFr() {
        return excerptFr;
    }

    public String getContentEn() {
        return contentEn;
    }

    public String getContentFr() {
        return contentFr;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getHeroImageUrl() {
        return heroImageUrl;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public Boolean getIsMostRead() {
        return isMostRead;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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

    public void setExcerptEn(String excerptEn) {
        this.excerptEn = excerptEn;
    }

    public void setExcerptFr(String excerptFr) {
        this.excerptFr = excerptFr;
    }

    public void setContentEn(String contentEn) {
        this.contentEn = contentEn;
    }

    public void setContentFr(String contentFr) {
        this.contentFr = contentFr;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setHeroImageUrl(String heroImageUrl) {
        this.heroImageUrl = heroImageUrl;
    }

    public void setIsFeatured(Boolean featured) {
        isFeatured = featured;
    }

    public void setIsMostRead(Boolean mostRead) {
        isMostRead = mostRead;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }
}