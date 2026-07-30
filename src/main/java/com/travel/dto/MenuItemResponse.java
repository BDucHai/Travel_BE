package com.travel.dto;

public class MenuItemResponse {

    private Long id;
    private String label;
    private String slug;
    private Integer value;
    private String url;
    private string content;
    public MenuItemResponse() {
    }

    public MenuItemResponse(Long id, String label, String slug, Integer value, String url) {
        this.id = id;
        this.label = label;
        this.slug = slug;
        this.value = value;
        this.url = url;
    }

    public MenuItemResponse(Long id, String label, String slug, Integer value, String url, String content) {
        this.id = id;
        this.label = label;
        this.slug = slug;
        this.value = value;
        this.url = url;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }


    public String getLabel() {
        return label;
    }

    public String getSlug() {
        return slug;
    }

    public Integer getValue() {
        return value;
    }

    public String getUrl() {
        return url;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
