package com.travel.service;

import com.travel.dto.TourCollectionRequest;
import com.travel.dto.TourCollectionResponse;
import com.travel.entity.TourCollection;
import com.travel.repository.TourCollectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourCollectionService {

    private final TourCollectionRepository tourCollectionRepository;

    public TourCollectionService(TourCollectionRepository tourCollectionRepository) {
        this.tourCollectionRepository = tourCollectionRepository;
    }

    // Public API
    public List<TourCollectionResponse> getTourCollections(String type, String lang) {
        List<TourCollection> collections;

        if (type != null && !type.isBlank()) {
            collections = tourCollectionRepository
                    .findByTypeAndIsActiveTrueOrderByDisplayOrderAsc(type.toUpperCase());
        } else {
            collections = tourCollectionRepository
                    .findByIsActiveTrueOrderByDisplayOrderAsc();
        }

        return collections.stream()
                .map(collection -> mapToResponse(collection, lang))
                .toList();
    }

    // Public API
    public TourCollectionResponse getTourCollectionDetail(String slug, String lang) {
        boolean isFrench = "fr".equalsIgnoreCase(lang);

        TourCollection collection = isFrench
                ? tourCollectionRepository.findBySlugFrAndIsActiveTrue(slug)
                    .orElseThrow(() -> new RuntimeException("Tour collection not found"))
                : tourCollectionRepository.findBySlugEnAndIsActiveTrue(slug)
                    .orElseThrow(() -> new RuntimeException("Tour collection not found"));

        return mapToResponse(collection, lang);
    }

    // Admin API
    public List<TourCollectionResponse> getAllTourCollectionsForAdmin(String type, String lang) {
        List<TourCollection> collections;

        if (type != null && !type.isBlank()) {
            collections = tourCollectionRepository.findByTypeOrderByDisplayOrderAsc(type.toUpperCase());
        } else {
            collections = tourCollectionRepository.findAllByOrderByDisplayOrderAsc();
        }

        return collections.stream()
                .map(collection -> mapToResponse(collection, lang))
                .toList();
    }

    // Admin API
    public TourCollectionResponse getTourCollectionById(Long id, String lang) {
        TourCollection collection = tourCollectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour collection not found"));

        return mapToResponse(collection, lang);
    }

    // Admin API
    public TourCollectionResponse createTourCollection(TourCollectionRequest request, String lang) {
        TourCollection collection = new TourCollection();

        collection.setType(
                request.getType() == null || request.getType().isBlank()
                        ? "COMBINED"
                        : request.getType().toUpperCase()
        );

        collection.setNameEn(request.getNameEn());
        collection.setNameFr(request.getNameFr());

        collection.setSlugEn(request.getSlugEn());
        collection.setSlugFr(request.getSlugFr());

        collection.setDescriptionEn(request.getDescriptionEn());
        collection.setDescriptionFr(request.getDescriptionFr());

        collection.setThumbnailUrl(request.getThumbnailUrl());

        collection.setDisplayOrder(request.getDisplayOrder() == null ? 0 : request.getDisplayOrder());
        collection.setIsActive(request.getIsActive() == null ? true : request.getIsActive());

        TourCollection saved = tourCollectionRepository.save(collection);

        return mapToResponse(saved, lang);
    }

    // Admin API
    public TourCollectionResponse updateTourCollection(Long id, TourCollectionRequest request, String lang) {
        TourCollection collection = tourCollectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour collection not found"));

        collection.setType(
                request.getType() == null || request.getType().isBlank()
                        ? "COMBINED"
                        : request.getType().toUpperCase()
        );

        collection.setNameEn(request.getNameEn());
        collection.setNameFr(request.getNameFr());

        collection.setSlugEn(request.getSlugEn());
        collection.setSlugFr(request.getSlugFr());

        collection.setDescriptionEn(request.getDescriptionEn());
        collection.setDescriptionFr(request.getDescriptionFr());

        collection.setThumbnailUrl(request.getThumbnailUrl());

        collection.setDisplayOrder(request.getDisplayOrder() == null ? 0 : request.getDisplayOrder());

        if (request.getIsActive() != null) {
            collection.setIsActive(request.getIsActive());
        }

        TourCollection updated = tourCollectionRepository.save(collection);

        return mapToResponse(updated, lang);
    }

    // Admin API
    public TourCollectionResponse updateTourCollectionStatus(Long id, Boolean isActive, String lang) {
        TourCollection collection = tourCollectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour collection not found"));

        collection.setIsActive(isActive == null ? true : isActive);

        TourCollection updated = tourCollectionRepository.save(collection);

        return mapToResponse(updated, lang);
    }

    // Admin API
    public void deleteTourCollection(Long id) {
        TourCollection collection = tourCollectionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour collection not found"));

        tourCollectionRepository.delete(collection);
    }

    private TourCollectionResponse mapToResponse(TourCollection collection, String lang) {
        boolean isFrench = "fr".equalsIgnoreCase(lang);

        return new TourCollectionResponse(
                collection.getId(),
                collection.getType(),
                isFrench ? collection.getNameFr() : collection.getNameEn(),
                isFrench ? collection.getSlugFr() : collection.getSlugEn(),
                isFrench ? collection.getDescriptionFr() : collection.getDescriptionEn(),
                collection.getThumbnailUrl(),
                collection.getDisplayOrder(),
                collection.getIsActive()
        );
    }
}