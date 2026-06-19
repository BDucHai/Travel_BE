package com.travel.dto;

public class TourItineraryResponse {

    private Long id;
    private Integer dayNumber;
    private String title;
    private String description;
    private String imageUrl;
    private Integer displayOrder;

    public TourItineraryResponse() {
    }

    public TourItineraryResponse(
            Long id,
            Integer dayNumber,
            String title,
            String description,
            String imageUrl,
            Integer displayOrder
    ) {
        this.id = id;
        this.dayNumber = dayNumber;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.displayOrder = displayOrder;
    }

    public Long getId() {
        return id;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
}