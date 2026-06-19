package com.travel.service;

import com.travel.dto.BannerRequest;
import com.travel.dto.BannerResponse;
import com.travel.entity.Banner;
import com.travel.repository.BannerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerService {

    private final BannerRepository bannerRepository;

    public BannerService(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    // Public API: chỉ lấy banner active
    public List<BannerResponse> getBanners(String position) {
        String bannerPosition = position == null || position.isBlank()
                ? "HOME_HERO"
                : position.toUpperCase();

        return bannerRepository.findByPositionAndIsActiveTrueOrderByDisplayOrderAsc(bannerPosition)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Admin API: lấy tất cả banner, kể cả inactive
    public List<BannerResponse> getAllBannersForAdmin() {
        return bannerRepository.findAllByOrderByDisplayOrderAsc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public BannerResponse createBanner(BannerRequest request) {
        Banner banner = new Banner();

        banner.setImageUrl(request.getImageUrl());
        banner.setMobileImageUrl(request.getMobileImageUrl());
        banner.setLinkUrl(request.getLinkUrl());
        banner.setPosition(
                request.getPosition() == null || request.getPosition().isBlank()
                        ? "HOME_HERO"
                        : request.getPosition().toUpperCase()
        );
        banner.setDisplayOrder(request.getDisplayOrder() == null ? 0 : request.getDisplayOrder());
        banner.setIsActive(request.getIsActive() == null ? true : request.getIsActive());

        Banner savedBanner = bannerRepository.save(banner);

        return mapToResponse(savedBanner);
    }

    public BannerResponse updateBanner(Long id, BannerRequest request) {
        Banner banner = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banner not found"));

        banner.setImageUrl(request.getImageUrl());
        banner.setMobileImageUrl(request.getMobileImageUrl());
        banner.setLinkUrl(request.getLinkUrl());

        if (request.getPosition() != null && !request.getPosition().isBlank()) {
            banner.setPosition(request.getPosition().toUpperCase());
        }

        banner.setDisplayOrder(request.getDisplayOrder() == null ? 0 : request.getDisplayOrder());
        banner.setIsActive(request.getIsActive() == null ? true : request.getIsActive());

        Banner updatedBanner = bannerRepository.save(banner);

        return mapToResponse(updatedBanner);
    }

    public void deleteBanner(Long id) {
        Banner banner = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banner not found"));

        bannerRepository.delete(banner);
    }

    private BannerResponse mapToResponse(Banner banner) {
        return new BannerResponse(
                banner.getId(),
                banner.getImageUrl(),
                banner.getMobileImageUrl(),
                banner.getLinkUrl(),
                banner.getPosition(),
                banner.getDisplayOrder(),
                banner.getIsActive()
        );
    }
}