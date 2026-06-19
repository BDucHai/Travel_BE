package com.travel.dto;

public class ImageUploadResponse {

    private String url;
    private String publicId;

    public ImageUploadResponse() {
    }

    public ImageUploadResponse(String url, String publicId) {
        this.url = url;
        this.publicId = publicId;
    }

    public String getUrl() {
        return url;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }
}