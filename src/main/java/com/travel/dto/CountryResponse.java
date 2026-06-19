package com.travel.dto;

public class CountryResponse {

    private Long id;
    private String code;
    private String name;
    private String slug;
    private String description;
    private String thumbnailUrl;
    private Boolean isActive;

    public CountryResponse() {
    }

    public CountryResponse(
            Long id,
            String code,
            String name,
            String slug,
            String description,
            String thumbnailUrl,
            Boolean isActive
    ) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
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

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}