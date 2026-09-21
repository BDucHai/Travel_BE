package com.travel.dto;

import com.travel.dto.*;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;


public class GlobalSearchResponse {

     private Page<BlogResponse> blogs;

     private Page<TourResponse> tours;

     public GlobalSearchResponse() {
    }

    public GlobalSearchResponse(
            Page<BlogResponse> blogs,
            Page<TourResponse> tours
    ) {
        this.blogs = blogs;
        this.tours = tours;
    }

    public Page<BlogResponse> getBlogs() {
        return blogs;
    }

    public void setBlogs(Page<BlogResponse> blogs) {
        this.blogs = blogs;
    }

    public Page<TourResponse> getTours() {
        return tours;
    }

    public void setTours(Page<TourResponse> tours) {
        this.tours = tours;
    }
}