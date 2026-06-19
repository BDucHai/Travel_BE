package com.travel.dto;

import java.time.LocalDateTime;
import java.util.List;

public class AdminBlogResponse {

    private Long id;
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

    private Integer viewCount;
    private Boolean isFeatured;
    private Boolean isMostRead;
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;

    private List<Long> relatedTourIds;
    private List<AdminBlogRelatedTourResponse> relatedTours;

    public AdminBlogResponse() {
    }

    public AdminBlogResponse(
            Long id,
            String authorName,
            String titleEn,
            String titleFr,
            String slugEn,
            String slugFr,
            String excerptEn,
            String excerptFr,
            String contentEn,
            String contentFr,
            String thumbnailUrl,
            String heroImageUrl,
            Integer viewCount,
            Boolean isFeatured,
            Boolean isMostRead,
            String status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime publishedAt,
            List<Long> relatedTourIds,
            List<AdminBlogRelatedTourResponse> relatedTours
    ) {
        this.id = id;
        this.authorName = authorName;
        this.titleEn = titleEn;
        this.titleFr = titleFr;
        this.slugEn = slugEn;
        this.slugFr = slugFr;
        this.excerptEn = excerptEn;
        this.excerptFr = excerptFr;
        this.contentEn = contentEn;
        this.contentFr = contentFr;
        this.thumbnailUrl = thumbnailUrl;
        this.heroImageUrl = heroImageUrl;
        this.viewCount = viewCount;
        this.isFeatured = isFeatured;
        this.isMostRead = isMostRead;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.publishedAt = publishedAt;
        this.relatedTourIds = relatedTourIds;
        this.relatedTours = relatedTours;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
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

	public String getExcerptEn() {
		return excerptEn;
	}

	public void setExcerptEn(String excerptEn) {
		this.excerptEn = excerptEn;
	}

	public String getExcerptFr() {
		return excerptFr;
	}

	public void setExcerptFr(String excerptFr) {
		this.excerptFr = excerptFr;
	}

	public String getContentEn() {
		return contentEn;
	}

	public void setContentEn(String contentEn) {
		this.contentEn = contentEn;
	}

	public String getContentFr() {
		return contentFr;
	}

	public void setContentFr(String contentFr) {
		this.contentFr = contentFr;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getHeroImageUrl() {
		return heroImageUrl;
	}

	public void setHeroImageUrl(String heroImageUrl) {
		this.heroImageUrl = heroImageUrl;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Boolean getIsFeatured() {
		return isFeatured;
	}

	public void setIsFeatured(Boolean isFeatured) {
		this.isFeatured = isFeatured;
	}

	public Boolean getIsMostRead() {
		return isMostRead;
	}

	public void setIsMostRead(Boolean isMostRead) {
		this.isMostRead = isMostRead;
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

	public LocalDateTime getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(LocalDateTime publishedAt) {
		this.publishedAt = publishedAt;
	}

	public List<Long> getRelatedTourIds() {
		return relatedTourIds;
	}

	public void setRelatedTourIds(List<Long> relatedTourIds) {
		this.relatedTourIds = relatedTourIds;
	}

	public List<AdminBlogRelatedTourResponse> getRelatedTours() {
		return relatedTours;
	}

	public void setRelatedTours(List<AdminBlogRelatedTourResponse> relatedTours) {
		this.relatedTours = relatedTours;
	}
    
    

}