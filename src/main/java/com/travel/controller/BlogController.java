package com.travel.controller;

import com.travel.dto.BlogResponse;
import com.travel.dto.PaginationResponse;
import com.travel.service.BlogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@CrossOrigin(origins = "*")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public PaginationResponse<BlogResponse> getBlogs(
            @RequestParam(defaultValue = "en") String lang,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "9") Integer limit
    ) {
        return blogService.getBlogs(lang, page, limit);
    }

    @GetMapping("/most-read")
    public List<BlogResponse> getMostReadBlogs(
            @RequestParam(defaultValue = "en") String lang
    ) {
        return blogService.getMostReadBlogs(lang);
    }

    @GetMapping("/{slug}")
    public BlogResponse getBlogDetail(
            @PathVariable String slug,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return blogService.getBlogDetail(slug, lang);
    }
}