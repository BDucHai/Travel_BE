package com.travel.service;

import com.travel.dto.TourStyleRequest;
import com.travel.dto.TourStyleResponse;
import com.travel.entity.TourStyle;
import com.travel.repository.TourStyleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourStyleService {

    private final TourStyleRepository tourStyleRepository;

    public TourStyleService(TourStyleRepository tourStyleRepository) {
        this.tourStyleRepository = tourStyleRepository;
    }

    // Public API
    public List<TourStyleResponse> getAllTourStyles(String lang) {
        return tourStyleRepository.findByIsActiveTrueOrderByDisplayOrderAsc()
                .stream()
                .map(tourStyle -> mapToResponse(tourStyle, lang))
                .toList();
    }

    // Public API
    public TourStyleResponse getTourStyleDetail(String slug, String lang) {
        boolean isFrench = "fr".equalsIgnoreCase(lang);

        TourStyle tourStyle = isFrench
                ? tourStyleRepository.findBySlugFrAndIsActiveTrue(slug)
                    .orElseThrow(() -> new RuntimeException("Tour style not found"))
                : tourStyleRepository.findBySlugEnAndIsActiveTrue(slug)
                    .orElseThrow(() -> new RuntimeException("Tour style not found"));

        return mapToResponse(tourStyle, lang);
    }

    // Admin API
    public List<TourStyleResponse> getAllTourStylesForAdmin(String lang) {
        return tourStyleRepository.findAllByOrderByDisplayOrderAsc()
                .stream()
                .map(tourStyle -> mapToResponse(tourStyle, lang))
                .toList();
    }

    // Admin API
    public TourStyleResponse getTourStyleById(Long id, String lang) {
        TourStyle tourStyle = tourStyleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour style not found"));

        return mapToResponse(tourStyle, lang);
    }

    // Admin API
    public TourStyleResponse createTourStyle(TourStyleRequest request, String lang) {
        TourStyle tourStyle = new TourStyle();

        tourStyle.setNameEn(request.getNameEn());
        tourStyle.setNameFr(request.getNameFr());

        tourStyle.setSlugEn(request.getSlugEn());
        tourStyle.setSlugFr(request.getSlugFr());

        tourStyle.setDescriptionEn(request.getDescriptionEn());
        tourStyle.setDescriptionFr(request.getDescriptionFr());

        tourStyle.setIconUrl(request.getIconUrl());
        tourStyle.setThumbnailUrl(request.getThumbnailUrl());

        tourStyle.setDisplayOrder(request.getDisplayOrder() == null ? 0 : request.getDisplayOrder());
        tourStyle.setIsActive(request.getIsActive() == null ? true : request.getIsActive());

        TourStyle saved = tourStyleRepository.save(tourStyle);

        return mapToResponse(saved, lang);
    }

    // Admin API
    public TourStyleResponse updateTourStyle(Long id, TourStyleRequest request, String lang) {
        TourStyle tourStyle = tourStyleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour style not found"));

        tourStyle.setNameEn(request.getNameEn());
        tourStyle.setNameFr(request.getNameFr());

        tourStyle.setSlugEn(request.getSlugEn());
        tourStyle.setSlugFr(request.getSlugFr());

        tourStyle.setDescriptionEn(request.getDescriptionEn());
        tourStyle.setDescriptionFr(request.getDescriptionFr());

        tourStyle.setIconUrl(request.getIconUrl());
        tourStyle.setThumbnailUrl(request.getThumbnailUrl());

        tourStyle.setDisplayOrder(request.getDisplayOrder() == null ? 0 : request.getDisplayOrder());

        if (request.getIsActive() != null) {
            tourStyle.setIsActive(request.getIsActive());
        }

        TourStyle updated = tourStyleRepository.save(tourStyle);

        return mapToResponse(updated, lang);
    }

    // Admin API
    public TourStyleResponse updateTourStyleStatus(Long id, Boolean isActive, String lang) {
        TourStyle tourStyle = tourStyleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour style not found"));

        tourStyle.setIsActive(isActive == null ? true : isActive);

        TourStyle updated = tourStyleRepository.save(tourStyle);

        return mapToResponse(updated, lang);
    }

    // Admin API
    public void deleteTourStyle(Long id) {
        TourStyle tourStyle = tourStyleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour style not found"));

        tourStyleRepository.delete(tourStyle);
    }

    private TourStyleResponse mapToResponse(TourStyle tourStyle, String lang) {
        boolean isFrench = "fr".equalsIgnoreCase(lang);

        return new TourStyleResponse(
                tourStyle.getId(),
                isFrench ? tourStyle.getNameFr() : tourStyle.getNameEn(),
                isFrench ? tourStyle.getSlugFr() : tourStyle.getSlugEn(),
                isFrench ? tourStyle.getDescriptionFr() : tourStyle.getDescriptionEn(),
                tourStyle.getIconUrl(),
                tourStyle.getThumbnailUrl(),
                tourStyle.getDisplayOrder(),
                tourStyle.getIsActive()
        );
    }
}