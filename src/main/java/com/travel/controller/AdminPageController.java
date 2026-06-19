package com.travel.controller;

import com.travel.dto.PageRequest;
import com.travel.dto.PageResponse;
import com.travel.service.PageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/pages")
@CrossOrigin(origins = "*")
public class AdminPageController {

    private final PageService pageService;

    public AdminPageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping
    public List<PageResponse> getAllPages(
            @RequestParam(defaultValue = "en") String lang
    ) {
        return pageService.getAllPagesForAdmin(lang);
    }

    @GetMapping("/{id}")
    public PageResponse getPageById(
            @PathVariable Long id,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return pageService.getPageById(id, lang);
    }

    @PostMapping
    public PageResponse createPage(
            @RequestBody PageRequest request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return pageService.createPage(request, lang);
    }

    @PutMapping("/{id}")
    public PageResponse updatePage(
            @PathVariable Long id,
            @RequestBody PageRequest request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return pageService.updatePage(id, request, lang);
    }

    @PatchMapping("/{id}/status")
    public PageResponse updatePageStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return pageService.updatePageStatus(id, request.get("status"), lang);
    }

    @DeleteMapping("/{id}")
    public String deletePage(@PathVariable Long id) {
        pageService.deletePage(id);
        return "Delete page successfully";
    }
}