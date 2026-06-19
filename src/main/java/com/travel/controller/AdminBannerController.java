package com.travel.controller;

import com.travel.dto.BannerRequest;
import com.travel.dto.BannerResponse;
import com.travel.service.BannerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/banners")
@CrossOrigin(origins = "*")
public class AdminBannerController {

    private final BannerService bannerService;

    public AdminBannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping
    public List<BannerResponse> getAllBanners() {
        return bannerService.getAllBannersForAdmin();
    }

    @PostMapping
    public BannerResponse createBanner(@RequestBody BannerRequest request) {
        return bannerService.createBanner(request);
    }

    @PutMapping("/{id}")
    public BannerResponse updateBanner(
            @PathVariable Long id,
            @RequestBody BannerRequest request
    ) {
        return bannerService.updateBanner(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteBanner(@PathVariable Long id) {
        bannerService.deleteBanner(id);
        return "Delete banner successfully";
    }
}