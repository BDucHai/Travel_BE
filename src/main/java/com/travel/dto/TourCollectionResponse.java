package com.travel.dto;

public class TourCollectionResponse {

    private Long id;
    private String type;
    private String name;
    private String slug;
    private String description;
    private String thumbnailUrl;
    private Integer displayOrder;
    private Boolean isActive;

    public TourCollectionResponse() {
    }

    public TourCollectionResponse(
            Long id,
            String type,
            String name,
            String slug,
            String description,
            String thumbnailUrl,
            Integer displayOrder,
            Boolean isActive
    ) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.displayOrder = displayOrder;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}