package com.travel.dto;

public class PageRequest {

    private String pageKey;

    private String titleEn;
    private String titleFr;

    private String slugEn;
    private String slugFr;

    private String contentEn;
    private String contentFr;

    private String status;

    public PageRequest() {
    }

    public String getPageKey() {
        return pageKey;
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

    public String getContentEn() {
        return contentEn;
    }

    public String getContentFr() {
        return contentFr;
    }

    public String getStatus() {
        return status;
    }

    public void setPageKey(String pageKey) {
        this.pageKey = pageKey;
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

    public void setContentEn(String contentEn) {
        this.contentEn = contentEn;
    }

    public void setContentFr(String contentFr) {
        this.contentFr = contentFr;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}