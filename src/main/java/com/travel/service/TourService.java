package com.travel.service;

import com.travel.dto.*;
import com.travel.entity.*;
import com.travel.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.travel.dto.PaginationResponse;
import com.travel.dto.TourResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Column;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class TourService {

        private final TourRepository tourRepository;
        private final TourImageRepository tourImageRepository;
        private final TourDestinationRepository tourDestinationRepository;
        private final TourStyleRepository tourStyleRepository;
        private final TourCollectionRepository tourCollectionRepository;
        private final DestinationRepository destinationRepository;
        private final TourItineraryRepository tourItineraryRepository;
        private final CloudinaryService cloudinaryService;

        public TourService(
                TourRepository tourRepository,
                TourImageRepository tourImageRepository,
                TourDestinationRepository tourDestinationRepository,
                TourStyleRepository tourStyleRepository,
                TourCollectionRepository tourCollectionRepository,
                DestinationRepository destinationRepository,
                TourItineraryRepository tourItineraryRepository,
                CloudinaryService cloudinaryService
        ) {
        this.tourRepository = tourRepository;
        this.tourImageRepository = tourImageRepository;
        this.tourDestinationRepository = tourDestinationRepository;
        this.tourStyleRepository = tourStyleRepository;
        this.tourCollectionRepository = tourCollectionRepository;
        this.destinationRepository = destinationRepository;
        this.tourItineraryRepository = tourItineraryRepository;
        this.cloudinaryService = cloudinaryService;
        }

    // Public API
 // Public API: get tours with filters + pagination
    public PaginationResponse<TourResponse> getTours(
            Integer duration,
            String styleSlug,
            String collectionSlug,
            String destinationSlug,
            Integer page,
            Integer limit,
            String lang
    ) {
        boolean isFrench = "fr".equalsIgnoreCase(lang);

        int pageNumber = page == null || page < 0 ? 0 : page;
        int pageSize = limit == null || limit <= 0 ? 6 : limit;

        Pageable pageable = PageRequest.of(
                pageNumber,
                pageSize,
                Sort.by("createdAt").descending()
        );

        Page<Tour> tourPage;

        if (duration != null) {
            tourPage = tourRepository.findByDurationDaysAndStatusAndIsActiveTrue(
                    duration,
                    "PUBLISHED",
                    pageable
            );
        } else if (styleSlug != null && !styleSlug.isBlank()) {
            tourPage = isFrench
                    ? tourRepository.findPublishedByStyleSlugFrPage(styleSlug, pageable)
                    : tourRepository.findPublishedByStyleSlugEnPage(styleSlug, pageable);
        } else if (collectionSlug != null && !collectionSlug.isBlank()) {
            tourPage = isFrench
                    ? tourRepository.findPublishedByCollectionSlugFrPage(collectionSlug, pageable)
                    : tourRepository.findPublishedByCollectionSlugEnPage(collectionSlug, pageable);
        } else if (destinationSlug != null && !destinationSlug.isBlank()) {
            tourPage = isFrench
                    ? tourRepository.findPublishedByDestinationSlugFrPage(destinationSlug, pageable)
                    : tourRepository.findPublishedByDestinationSlugEnPage(destinationSlug, pageable);
        } else {
            tourPage = tourRepository.findByStatusAndIsActiveTrue(
                    "PUBLISHED",
                    pageable
            );
        }

        List<TourResponse> data = tourPage.getContent()
                .stream()
                .map(tour -> mapToResponse(tour, lang,false))
                .toList();

        return new PaginationResponse<>(
                data,
                tourPage.getNumber(),
                tourPage.getSize(),
                tourPage.getTotalElements(),
                tourPage.getTotalPages(),
                tourPage.isFirst(),
                tourPage.isLast()
        );
    }

    // Public API
    public TourResponse getTourDetail(String slug, String lang) {
        Tour tour = tourRepository.findPublishedByAnySlug(slug)
                .orElseThrow(() -> new RuntimeException("Tour not found"));

        return mapToResponse(tour, lang, true);
    }

    // Admin API
    public PaginationResponse<AdminTourResponse> getAllToursForAdmin(
        Integer page,
        Integer limit,
        String titleEn
        ) {
        int pageNumber = page == null || page < 0 ? 0 : page;
        int pageSize = limit == null || limit <= 0 ? 10 : limit;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Tour> tourPage;

        if (titleEn != null && !titleEn.trim().isEmpty()) {
                tourPage = tourRepository
                        .findByTitleEnContainingIgnoreCaseOrderByCreatedAtDesc(
                                titleEn.trim(),
                                pageable
                        );
        } else {
                tourPage = tourRepository.findAllByOrderByCreatedAtDesc(pageable);
        }

        List<AdminTourResponse> data = tourPage.getContent()
                .stream()
                .map(tour -> mapToAdminResponse(tour, true))
                .toList();

        return new PaginationResponse<>(
                data,
                tourPage.getNumber(),
                tourPage.getSize(),
                tourPage.getTotalElements(),
                tourPage.getTotalPages(),
                tourPage.isFirst(),
                tourPage.isLast()
        );
        }

    // Admin API
    public AdminTourResponse getTourById(Long id) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour not found"));

        return mapToAdminResponse(tour, true);
    }
    // Admin API
    public TourResponse createTour(TourRequest request, String lang) {
        Tour tour = new Tour();

        fillTourData(tour, request);

        tour.setIsFeatured(request.getIsFeatured() == null ? false : request.getIsFeatured());
        tour.setIsActive(request.getIsActive() == null ? true : request.getIsActive());
        tour.setStatus(
                request.getStatus() == null || request.getStatus().isBlank()
                        ? "DRAFT"
                        : request.getStatus().toUpperCase()
        );

        setStyles(tour, request.getStyleIds());
        setCollections(tour, request.getCollectionIds());

        Tour saved = tourRepository.save(tour);

        return mapToResponse(saved, lang , false);
    }

    // Admin API
    public TourResponse updateTour(Long id, TourRequest request, String lang) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour not found"));

        fillTourData(tour, request);

        tour.setIsFeatured(request.getIsFeatured() == null ? false : request.getIsFeatured());

        if (request.getIsActive() != null) {
            tour.setIsActive(request.getIsActive());
        }

        if (request.getStatus() != null && !request.getStatus().isBlank()) {
            tour.setStatus(request.getStatus().toUpperCase());
        }

        setStyles(tour, request.getStyleIds());
        setCollections(tour, request.getCollectionIds());

        Tour updated = tourRepository.save(tour);

        return mapToResponse(updated, lang , false);
    }

    // Admin API
    public TourResponse updateTourStatus(Long id, String status, String lang) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour not found"));

        tour.setStatus(status.toUpperCase());

        Tour updated = tourRepository.save(tour);

        return mapToResponse(updated, lang,false);
    }

    // Admin API
    public TourResponse addImage(Long tourId, TourImageRequest request, String lang) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("Tour not found"));

        TourImage image = new TourImage();
        image.setTour(tour);
        image.setImageUrl(request.getImageUrl());
        image.setDisplayOrder(request.getDisplayOrder() == null ? 0 : request.getDisplayOrder());

        tourImageRepository.save(image);

        return mapToResponse(tour, lang,false);
    }

    // Admin API
    public void deleteImage(Long imageId) {
        TourImage image = tourImageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Tour image not found"));

        tourImageRepository.delete(image);
    }

    // Admin API
    public TourResponse addDestination(Long tourId, TourDestinationRequest request, String lang) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("Tour not found"));

        Destination destination = destinationRepository.findById(request.getDestinationId())
                .orElseThrow(() -> new RuntimeException("Destination not found"));

        TourDestination tourDestination = new TourDestination();
        tourDestination.setTour(tour);
        tourDestination.setDestination(destination);
        tourDestination.setDayOrder(request.getDayOrder());
        tourDestination.setDisplayOrder(request.getDisplayOrder() == null ? 0 : request.getDisplayOrder());

        tourDestinationRepository.save(tourDestination);

        return mapToResponse(tour, lang,false);
    }

    // Admin API
    public void deleteTourDestination(Long tourDestinationId) {
        TourDestination tourDestination = tourDestinationRepository.findById(tourDestinationId)
                .orElseThrow(() -> new RuntimeException("Tour destination not found"));

        tourDestinationRepository.delete(tourDestination);
    }

    // Admin API
    public TourResponse updateStyles(Long tourId, List<Long> styleIds, String lang) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("Tour not found"));

        setStyles(tour, styleIds);

        Tour updated = tourRepository.save(tour);

        return mapToResponse(updated, lang,false);
    }

    // Admin API
    public TourResponse updateCollections(Long tourId, List<Long> collectionIds, String lang) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("Tour not found"));

        setCollections(tour, collectionIds);

        Tour updated = tourRepository.save(tour);

        return mapToResponse(updated, lang,false);
    }

    @Transactional
    public void deleteTour(Long id) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour not found"));

        tourImageRepository.deleteByTourId(id);
        tourDestinationRepository.deleteByTourId(id);
        tourItineraryRepository.deleteByTourId(id);

        tour.getStyles().clear();
        tour.getCollections().clear();

        tourRepository.delete(tour);
    }

    private void fillTourData(Tour tour, TourRequest request) {
        tour.setCode(request.getCode());
        tour.setDurationDays(request.getDurationDays());
        tour.setPriceFrom(request.getPriceFrom());
        tour.setGroupSize(request.getGroupSize());

        tour.setTitleEn(request.getTitleEn());
        tour.setTitleFr(request.getTitleFr());

        tour.setSlugEn(request.getSlugEn());
        tour.setSlugFr(request.getSlugFr());

        tour.setShortDescriptionEn(request.getShortDescriptionEn());
        tour.setShortDescriptionFr(request.getShortDescriptionFr());

        tour.setOverviewEn(request.getOverviewEn());
        tour.setOverviewFr(request.getOverviewFr());

        tour.setItineraryEn(request.getItineraryEn());
        tour.setItineraryFr(request.getItineraryFr());

        tour.setInclusionEn(request.getInclusionEn());
        tour.setInclusionFr(request.getInclusionFr());

        tour.setExclusionEn(request.getExclusionEn());
        tour.setExclusionFr(request.getExclusionFr());

        tour.setFeaturedImageUrl(request.getFeaturedImageUrl());
    }

    private void setStyles(Tour tour, List<Long> styleIds) {
        if (styleIds == null) {
            return;
        }

        Set<TourStyle> styles = new HashSet<>(tourStyleRepository.findAllById(styleIds));
        tour.setStyles(styles);
    }

       private void setDestinations(Tour tour, List<Long> destinationIds) {
                if (destinationIds == null) {
                        return;
                }

                Set<Destination> destinations = new HashSet<>(destinationRepository.findAllById(destinationIds));
                tour.setDestinations(destinations);
                }

    private void setCollections(Tour tour, List<Long> collectionIds) {
        if (collectionIds == null) {
            return;
        }

        Set<TourCollection> collections = new HashSet<>(tourCollectionRepository.findAllById(collectionIds));
        tour.setCollections(collections);
    }
    
    @Transactional
    public TourResponse createTourWithImages(
        TourRequest request,
        MultipartFile featuredImage,
        MultipartFile[] images,
        MultipartFile[] itineraryImages,
        String lang
) {
        Tour tour = new Tour();

        // 1. Fill dữ liệu cơ bản của tour
        fillTourData(tour, request);

        // set featured image từ request luôn
        tour.setFeaturedImageUrl(request.getFeaturedImageUrl());

        tour.setIsFeatured(request.getIsFeatured() == null ? false : request.getIsFeatured());
        tour.setIsActive(request.getIsActive() == null ? true : request.getIsActive());
        tour.setStatus(
                request.getStatus() == null || request.getStatus().isBlank()
                        ? "DRAFT"
                        : request.getStatus().toUpperCase()
        );

        setStyles(tour, request.getStyleIds());
        setCollections(tour, request.getCollectionIds());
        setDestinations(tour, request.getDestinationIds());

        Tour saved = tourRepository.save(tour);

        // 2. Lưu gallery image từ request.getImageUrls()
        if (request.getImageUrls() != null && !request.getImageUrls().isEmpty()) {
                int displayOrder = 1;

                for (String imageUrl : request.getImageUrls()) {
                if (imageUrl != null && !imageUrl.isBlank()) {
                        TourImage tourImage = new TourImage();
                        tourImage.setTour(saved);
                        tourImage.setImageUrl(imageUrl);
                        tourImage.setDisplayOrder(displayOrder++);

                        tourImageRepository.save(tourImage);
                }
                }
        }

        // 3. Lưu itinerary từng ngày + upload ảnh itinerary nếu vẫn còn dùng file
        saveItineraryDays(saved, request, itineraryImages);

        // 4. Trả về detail có itineraryDays
        return mapToResponse(saved, lang, true);
        }

        @Transactional
        public TourResponse updateTourWithImages(
                Long id,
                TourRequest request,
                MultipartFile featuredImage,
                MultipartFile[] images,
                MultipartFile[] itineraryImages,
                String lang
        ) {
        // 1. FIND EXISTING TOUR
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tour not found: " + id));

        // 2. UPDATE BASIC DATA
        fillTourData(tour, request);

        // featured image (giữ logic giống create)
        if (request.getFeaturedImageUrl() != null) {
                tour.setFeaturedImageUrl(request.getFeaturedImageUrl());
        }

        tour.setIsFeatured(
                request.getIsFeatured() != null && request.getIsFeatured()
        );

        tour.setIsActive(
                request.getIsActive() == null ? true : request.getIsActive()
        );

        tour.setStatus(
                request.getStatus() == null || request.getStatus().isBlank()
                        ? "DRAFT"
                        : request.getStatus().toUpperCase()
        );

        // 3. UPDATE RELATIONS (styles, collections, destinations)
        setStyles(tour, request.getStyleIds());
        setCollections(tour, request.getCollectionIds());
        setDestinations(tour, request.getDestinationIds());

        Tour saved = tourRepository.save(tour);

        // 4. DELETE OLD IMAGES (IMPORTANT)
        tourImageRepository.deleteByTourId(saved.getId());

        // 5. SAVE NEW GALLERY IMAGES
        if (request.getImageUrls() != null && !request.getImageUrls().isEmpty()) {
                int displayOrder = 1;

                for (String imageUrl : request.getImageUrls()) {
                if (imageUrl != null && !imageUrl.isBlank()) {
                        TourImage img = new TourImage();
                        img.setTour(saved);
                        img.setImageUrl(imageUrl);
                        img.setDisplayOrder(displayOrder++);

                        tourImageRepository.save(img);
                }
                }
        }

        // 6. UPDATE ITINERARY DAYS
        itineraryDayRepository.deleteByTourId(saved.getId());
        saveItineraryDays(saved, request, itineraryImages);

        // 7. RETURN RESPONSE
        return mapToResponse(saved, lang, true);
        }
        
    
    private void saveItineraryDays(
            Tour savedTour,
            TourRequest request,
            MultipartFile[] itineraryImages
    ) {
        if (request.getItineraryDays() == null || request.getItineraryDays().isEmpty()) {
            return;
        }

        int defaultDisplayOrder = 1;

        for (TourItineraryRequest itemRequest : request.getItineraryDays()) {
            TourItinerary itinerary = new TourItinerary();

            itinerary.setTour(savedTour);
            itinerary.setDayNumber(itemRequest.getDayNumber());

            itinerary.setTitleEn(itemRequest.getTitleEn());
            itinerary.setTitleFr(itemRequest.getTitleFr());

            itinerary.setDescriptionEn(itemRequest.getDescriptionEn());
            itinerary.setDescriptionFr(itemRequest.getDescriptionFr());

            if (itemRequest.getDisplayOrder() != null) {
                itinerary.setDisplayOrder(itemRequest.getDisplayOrder());
            } else if (itemRequest.getDayNumber() != null) {
                itinerary.setDisplayOrder(itemRequest.getDayNumber());
            } else {
                itinerary.setDisplayOrder(defaultDisplayOrder);
            }

            String itineraryImageUrl = itemRequest.getImageUrl();

            Integer imageIndex = itemRequest.getImageIndex();

            if (imageIndex != null
                    && itineraryImages != null
                    && imageIndex >= 0
                    && imageIndex < itineraryImages.length
                    && itineraryImages[imageIndex] != null
                    && !itineraryImages[imageIndex].isEmpty()) {

                ImageUploadResponse uploaded = cloudinaryService.uploadImage(
                        itineraryImages[imageIndex],
                        "travel-website/tours/itineraries"
                );

                itineraryImageUrl = uploaded.getUrl();
            }

            itinerary.setImageUrl(itineraryImageUrl);

            tourItineraryRepository.save(itinerary);

            defaultDisplayOrder++;
        }
    }

    private TourResponse mapToResponse(Tour tour, String lang, boolean includeItineraryDays) {
        boolean isFrench = "fr".equalsIgnoreCase(lang);

        List<String> imageUrls = tourImageRepository.findByTourIdOrderByDisplayOrderAsc(tour.getId())
                .stream()
                .map(TourImage::getImageUrl)
                .toList();

        List<String> destinationNames = tourDestinationRepository.findByTourIdOrderByDisplayOrderAsc(tour.getId())
                .stream()
                .map(td -> {
                    Destination d = td.getDestination();
                    return isFrench ? d.getNameFr() : d.getNameEn();
                })
                .toList();

        List<String> styleNames = tour.getStyles()
                .stream()
                .map(style -> isFrench ? style.getNameFr() : style.getNameEn())
                .toList();

        List<String> collectionNames = tour.getCollections()
                .stream()
                .map(collection -> isFrench ? collection.getNameFr() : collection.getNameEn())
                .toList();

        List<TourItineraryResponse> itineraryDays = null;

        if (includeItineraryDays) {
            itineraryDays = tourItineraryRepository.findByTourIdOrderByDisplayOrderAsc(tour.getId())
                    .stream()
                    .map(item -> new TourItineraryResponse(
                            item.getId(),
                            item.getDayNumber(),
                            isFrench ? item.getTitleFr() : item.getTitleEn(),
                            isFrench ? item.getDescriptionFr() : item.getDescriptionEn(),
                            item.getImageUrl(),
                            item.getDisplayOrder()
                    ))
                    .toList();
        }

        return new TourResponse(
                tour.getId(),
                tour.getCode(),
                tour.getDurationDays(),
                tour.getPriceFrom(),
                tour.getGroupSize(),
                isFrench ? tour.getTitleFr() : tour.getTitleEn(),
                isFrench ? tour.getSlugFr() : tour.getSlugEn(),
                isFrench ? tour.getShortDescriptionFr() : tour.getShortDescriptionEn(),
                isFrench ? tour.getOverviewFr() : tour.getOverviewEn(),
                isFrench ? tour.getItineraryFr() : tour.getItineraryEn(),
                isFrench ? tour.getInclusionFr() : tour.getInclusionEn(),
                isFrench ? tour.getExclusionFr() : tour.getExclusionEn(),
                tour.getFeaturedImageUrl(),
                tour.getIsFeatured(),
                tour.getIsActive(),
                tour.getStatus(),
                tour.getCreatedAt(),
                imageUrls,
                destinationNames,
                styleNames,
                collectionNames,
                itineraryDays
        );
    }
    
    private AdminTourResponse mapToAdminResponse(Tour tour, boolean includeItineraryDays) {
        List<String> imageUrls = tourImageRepository.findByTourIdOrderByDisplayOrderAsc(tour.getId())
                .stream()
                .map(TourImage::getImageUrl)
                .toList();

        List<TourDestination> tourDestinations =
                tourDestinationRepository.findByTourIdOrderByDisplayOrderAsc(tour.getId());

        List<Long> destinationIds = tourDestinations
                .stream()
                .map(td -> td.getDestination().getId())
                .toList();

        List<String> destinationNamesEn = tourDestinations
                .stream()
                .map(td -> td.getDestination().getNameEn())
                .toList();

        List<String> destinationNamesFr = tourDestinations
                .stream()
                .map(td -> td.getDestination().getNameFr())
                .toList();

        List<Long> styleIds = tour.getStyles()
                .stream()
                .map(TourStyle::getId)
                .toList();

        List<String> styleNamesEn = tour.getStyles()
                .stream()
                .map(TourStyle::getNameEn)
                .toList();

        List<String> styleNamesFr = tour.getStyles()
                .stream()
                .map(TourStyle::getNameFr)
                .toList();

        List<Long> collectionIds = tour.getCollections()
                .stream()
                .map(TourCollection::getId)
                .toList();

        List<String> collectionNamesEn = tour.getCollections()
                .stream()
                .map(TourCollection::getNameEn)
                .toList();

        List<String> collectionNamesFr = tour.getCollections()
                .stream()
                .map(TourCollection::getNameFr)
                .toList();

        List<AdminTourItineraryResponse> itineraryDays = null;

        if (includeItineraryDays) {
            itineraryDays = tourItineraryRepository.findByTourIdOrderByDisplayOrderAsc(tour.getId())
                    .stream()
                    .map(item -> new AdminTourItineraryResponse(
                            item.getId(),
                            item.getDayNumber(),
                            item.getTitleEn(),
                            item.getTitleFr(),
                            item.getDescriptionEn(),
                            item.getDescriptionFr(),
                            item.getImageUrl(),
                            item.getDisplayOrder()
                    ))
                    .toList();
        }

        return new AdminTourResponse(
                tour.getId(),
                tour.getCode(),
                tour.getDurationDays(),
                tour.getPriceFrom(),
                tour.getGroupSize(),

                tour.getTitleEn(),
                tour.getTitleFr(),
                tour.getSlugEn(),
                tour.getSlugFr(),

                tour.getShortDescriptionEn(),
                tour.getShortDescriptionFr(),

                tour.getOverviewEn(),
                tour.getOverviewFr(),

                tour.getItineraryEn(),
                tour.getItineraryFr(),

                tour.getInclusionEn(),
                tour.getInclusionFr(),

                tour.getExclusionEn(),
                tour.getExclusionFr(),

                tour.getFeaturedImageUrl(),
                tour.getIsFeatured(),
                tour.getIsActive(),
                tour.getStatus(),

                tour.getCreatedAt(),
                tour.getUpdatedAt(),

                imageUrls,

                destinationIds,
                destinationNamesEn,
                destinationNamesFr,

                styleIds,
                styleNamesEn,
                styleNamesFr,

                collectionIds,
                collectionNamesEn,
                collectionNamesFr,

                itineraryDays
        );
    }
    
}