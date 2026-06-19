package com.travel.dto;

public class TourStyleResponse {

    private Long id;
    private String name;
    private String slug;
    private String description;
    private String iconUrl;
    private String thumbnailUrl;
    private Integer displayOrder;
    private Boolean isActive;

    public TourStyleResponse() {
    }

    public TourStyleResponse(
            Long id,
            String name,
            String slug,
            String description,
            String iconUrl,
            String thumbnailUrl,
            Integer displayOrder,
            Boolean isActive
    ) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.iconUrl = iconUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.displayOrder = displayOrder;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
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

    public String getIconUrl() {
        return iconUrl;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
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