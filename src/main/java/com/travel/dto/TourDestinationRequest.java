package com.travel.dto;

public class TourDestinationRequest {

    private Long destinationId;
    private Integer dayOrder;
    private Integer displayOrder;

    public TourDestinationRequest() {
    }

    public Long getDestinationId() {
        return destinationId;
    }

    public Integer getDayOrder() {
        return dayOrder;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDestinationId(Long destinationId) {
        this.destinationId = destinationId;
    }

    public void setDayOrder(Integer dayOrder) {
        this.dayOrder = dayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
}