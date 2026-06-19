package com.travel.controller;

import com.travel.dto.PageResponse;
import com.travel.service.PageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pages")
@CrossOrigin(origins = "*")
public class PageController {

    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping("/key/{pageKey}")
    public PageResponse getPageByKey(
            @PathVariable String pageKey,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return pageService.getPageByKey(pageKey, lang);
    }

    @GetMapping("/{slug}")
    public PageResponse getPageBySlug(
            @PathVariable String slug,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return pageService.getPageBySlug(slug, lang);
    }
}