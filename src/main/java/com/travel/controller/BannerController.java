package com.travel.controller;

import com.travel.dto.BannerResponse;
import com.travel.service.BannerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banners")
@CrossOrigin(origins = "*")
public class BannerController {

    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping
    public List<BannerResponse> getBanners(
            @RequestParam(defaultValue = "HOME_HERO") String position
    ) {
        return bannerService.getBanners(position);
    }
}