package com.travel.dto;

import java.util.List;

public class PaginationResponse<T> {

    private List<T> data;
    private int page;
    private int limit;
    private long totalItems;
    private int totalPages;
    private boolean first;
    private boolean last;

    public PaginationResponse() {
    }

    public PaginationResponse(
            List<T> data,
            int page,
            int limit,
            long totalItems,
            int totalPages,
            boolean first,
            boolean last
    ) {
        this.data = data;
        this.page = page;
        this.limit = limit;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.first = first;
        this.last = last;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}