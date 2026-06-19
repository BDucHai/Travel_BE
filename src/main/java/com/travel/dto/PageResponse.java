package com.travel.dto;

public class PageResponse {

    private Long id;
    private String pageKey;
    private String title;
    private String slug;
    private String content;
    private String status;

    public PageResponse() {
    }

    public PageResponse(
            Long id,
            String pageKey,
            String title,
            String slug,
            String content,
            String status
    ) {
        this.id = id;
        this.pageKey = pageKey;
        this.title = title;
        this.slug = slug;
        this.content = content;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getPageKey() {
        return pageKey;
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPageKey(String pageKey) {
        this.pageKey = pageKey;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}