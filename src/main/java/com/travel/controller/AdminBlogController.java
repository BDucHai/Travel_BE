package com.travel.controller;

import com.travel.dto.BlogRequest;
import com.travel.dto.BlogResponse;
import com.travel.service.BlogService;
import org.springframework.web.bind.annotation.*;
import com.travel.dto.AdminBlogResponse;
import com.travel.dto.AdminBlogResponse;
import com.travel.dto.PaginationResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/blogs")
@CrossOrigin(origins = "*")
public class AdminBlogController {

    private final BlogService blogService;

    public AdminBlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public PaginationResponse<AdminBlogResponse> getAllBlogs(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        return blogService.getAllBlogsForAdmin(page, limit);
    }

    @GetMapping("/{id}")
    public AdminBlogResponse getBlogById(@PathVariable Long id) {
        return blogService.getBlogById(id);
    }

    @PostMapping
    public BlogResponse createBlog(
            @RequestBody BlogRequest request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return blogService.createBlog(request, lang);
    }

    @PutMapping("/{id}")
    public BlogResponse updateBlog(
            @PathVariable Long id,
            @RequestBody BlogRequest request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return blogService.updateBlog(id, request, lang);
    }

    @PatchMapping("/{id}/status")
    public BlogResponse updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return blogService.updateBlogStatus(id, request.get("status"), lang);
    }

    @PatchMapping("/{id}/most-read")
    public BlogResponse updateMostRead(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return blogService.updateMostRead(id, request.get("isMostRead"), lang);
    }

    @DeleteMapping("/{id}")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "Delete blog successfully";
    }
}