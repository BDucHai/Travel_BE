package com.travel.dto;

public class AdminTourItineraryResponse {

    private Long id;
    private Integer dayNumber;
    private String titleEn;
    private String titleFr;
    private String descriptionEn;
    private String descriptionFr;
    private String imageUrl;
    private Integer displayOrder;

    public AdminTourItineraryResponse() {
    }

    public AdminTourItineraryResponse(
            Long id,
            Integer dayNumber,
            String titleEn,
            String titleFr,
            String descriptionEn,
            String descriptionFr,
            String imageUrl,
            Integer displayOrder
    ) {
        this.id = id;
        this.dayNumber = dayNumber;
        this.titleEn = titleEn;
        this.titleFr = titleFr;
        this.descriptionEn = descriptionEn;
        this.descriptionFr = descriptionFr;
        this.imageUrl = imageUrl;
        this.displayOrder = displayOrder;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(Integer dayNumber) {
		this.dayNumber = dayNumber;
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

	public String getDescriptionEn() {
		return descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}

	public String getDescriptionFr() {
		return descriptionFr;
	}

	public void setDescriptionFr(String descriptionFr) {
		this.descriptionFr = descriptionFr;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

    
}