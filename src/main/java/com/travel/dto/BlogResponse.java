package com.travel.dto;

import java.time.LocalDateTime;
import java.util.List;

public class BlogResponse {

    private Long id;
    private String authorName;

    private String title;
    private String slug;
    private String excerpt;
    private String content;

    private String thumbnailUrl;
    private String heroImageUrl;

    private Integer viewCount;
    private Boolean isFeatured;
    private Boolean isMostRead;
    private String status;
    private LocalDateTime publishedAt;
    private List<RelatedTourResponse> relatedTours;

    
    public BlogResponse() {
    }

    public BlogResponse(
            Long id,
            String authorName,
            String title,
            String slug,
            String excerpt,
            String content,
            String thumbnailUrl,
            String heroImageUrl,
            Integer viewCount,
            Boolean isFeatured,
            Boolean isMostRead,
            String status,
            LocalDateTime publishedAt,
            List<RelatedTourResponse> relatedTours
    ) {
        this.id = id;
        this.authorName = authorName;
        this.title = title;
        this.slug = slug;
        this.excerpt = excerpt;
        this.content = content;
        this.thumbnailUrl = thumbnailUrl;
        this.heroImageUrl = heroImageUrl;
        this.viewCount = viewCount;
        this.isFeatured = isFeatured;
        this.isMostRead = isMostRead;
        this.status = status;
        this.publishedAt = publishedAt;
        this.relatedTours = relatedTours;
    }

    public Long getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public String getContent() {
        return content;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getHeroImageUrl() {
        return heroImageUrl;
    }

    public Integer getViewCount() {
        return viewCount;
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
    
    public List<RelatedTourResponse> getRelatedTours() {
        return relatedTours;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setHeroImageUrl(String heroImageUrl) {
        this.heroImageUrl = heroImageUrl;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
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
    public void setRelatedTours(List<RelatedTourResponse> relatedTours) {
        this.relatedTours = relatedTours;
    }
}