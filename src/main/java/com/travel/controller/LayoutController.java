package com.travel.controller;

import com.travel.dto.MegaMenuResponse;
import com.travel.service.LayoutService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/layout")
@CrossOrigin(origins = "*")
public class LayoutController {

    private final LayoutService layoutService;

    public LayoutController(LayoutService layoutService) {
        this.layoutService = layoutService;
    }

    @GetMapping("/mega-menu")
    public MegaMenuResponse getMegaMenu(
            @RequestParam(defaultValue = "en") String lang
    ) {
        return layoutService.getMegaMenu(lang);
    }

    // API test nhanh để kiểm tra controller có được Spring scan không
    @GetMapping("/ping")
    public String ping() {
        return "Layout controller OK";
    }
}