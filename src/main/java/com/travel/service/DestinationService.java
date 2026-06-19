package com.travel.service;

import com.travel.dto.DestinationRequest;
import com.travel.dto.DestinationResponse;
import com.travel.entity.Country;
import com.travel.entity.Destination;
import com.travel.repository.CountryRepository;
import com.travel.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;
    private final CountryRepository countryRepository;

    public DestinationService(
            DestinationRepository destinationRepository,
            CountryRepository countryRepository
    ) {
        this.destinationRepository = destinationRepository;
        this.countryRepository = countryRepository;
    }

    // Public API
    public List<DestinationResponse> getDestinations(String region, String lang) {
        List<Destination> destinations;

        if (region != null && !region.isBlank()) {
            destinations = destinationRepository.findByRegionAndIsActiveTrueOrderByDisplayOrderAsc(region.toUpperCase());
        } else {
            destinations = destinationRepository.findByIsActiveTrueOrderByDisplayOrderAsc();
        }

        return destinations.stream()
                .map(destination -> mapToResponse(destination, lang))
                .toList();
    }

    // Public API
    public DestinationResponse getDestinationDetail(String slug, String lang) {
        boolean isFrench = "fr".equalsIgnoreCase(lang);

        Destination destination = isFrench
                ? destinationRepository.findBySlugFrAndIsActiveTrue(slug)
                    .orElseThrow(() -> new RuntimeException("Destination not found"))
                : destinationRepository.findBySlugEnAndIsActiveTrue(slug)
                    .orElseThrow(() -> new RuntimeException("Destination not found"));

        return mapToResponse(destination, lang);
    }

    // Admin API: lấy tất cả destination
    public List<DestinationResponse> getAllDestinationsForAdmin(String region, String lang) {
        List<Destination> destinations;

        if (region != null && !region.isBlank()) {
            destinations = destinationRepository.findByRegionOrderByDisplayOrderAsc(region.toUpperCase());
        } else {
            destinations = destinationRepository.findAllByOrderByDisplayOrderAsc();
        }

        return destinations.stream()
                .map(destination -> mapToResponse(destination, lang))
                .toList();
    }

    // Admin API: lấy destination theo id
    public DestinationResponse getDestinationById(Long id, String lang) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination not found"));

        return mapToResponse(destination, lang);
    }

    // Admin API: tạo destination
    public DestinationResponse createDestination(DestinationRequest request, String lang) {
        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country not found"));

        Destination destination = new Destination();

        destination.setCountry(country);
        destination.setRegion(request.getRegion() == null ? null : request.getRegion().toUpperCase());

        destination.setNameEn(request.getNameEn());
        destination.setNameFr(request.getNameFr());

        destination.setSlugEn(request.getSlugEn());
        destination.setSlugFr(request.getSlugFr());

        destination.setShortDescriptionEn(request.getShortDescriptionEn());
        destination.setShortDescriptionFr(request.getShortDescriptionFr());

        destination.setContentEn(request.getContentEn());
        destination.setContentFr(request.getContentFr());

        destination.setBestTimeToVisitEn(request.getBestTimeToVisitEn());
        destination.setBestTimeToVisitFr(request.getBestTimeToVisitFr());

        destination.setThumbnailUrl(request.getThumbnailUrl());
        destination.setHeroImageUrl(request.getHeroImageUrl());

        destination.setLatitude(request.getLatitude());
        destination.setLongitude(request.getLongitude());

        destination.setIsFeatured(request.getIsFeatured() == null ? false : request.getIsFeatured());
        destination.setIsActive(request.getIsActive() == null ? true : request.getIsActive());
        destination.setDisplayOrder(request.getDisplayOrder() == null ? 0 : request.getDisplayOrder());

        Destination saved = destinationRepository.save(destination);

        return mapToResponse(saved, lang);
    }

    // Admin API: sửa destination
    public DestinationResponse updateDestination(Long id, DestinationRequest request, String lang) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination not found"));

        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country not found"));

        destination.setCountry(country);
        destination.setRegion(request.getRegion() == null ? null : request.getRegion().toUpperCase());

        destination.setNameEn(request.getNameEn());
        destination.setNameFr(request.getNameFr());

        destination.setSlugEn(request.getSlugEn());
        destination.setSlugFr(request.getSlugFr());

        destination.setShortDescriptionEn(request.getShortDescriptionEn());
        destination.setShortDescriptionFr(request.getShortDescriptionFr());

        destination.setContentEn(request.getContentEn());
        destination.setContentFr(request.getContentFr());

        destination.setBestTimeToVisitEn(request.getBestTimeToVisitEn());
        destination.setBestTimeToVisitFr(request.getBestTimeToVisitFr());

        destination.setThumbnailUrl(request.getThumbnailUrl());
        destination.setHeroImageUrl(request.getHeroImageUrl());

        destination.setLatitude(request.getLatitude());
        destination.setLongitude(request.getLongitude());

        destination.setIsFeatured(request.getIsFeatured() == null ? false : request.getIsFeatured());

        if (request.getIsActive() != null) {
            destination.setIsActive(request.getIsActive());
        }

        destination.setDisplayOrder(request.getDisplayOrder() == null ? 0 : request.getDisplayOrder());

        Destination updated = destinationRepository.save(destination);

        return mapToResponse(updated, lang);
    }

    // Admin API: bật/tắt destination
    public DestinationResponse updateDestinationStatus(Long id, Boolean isActive, String lang) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination not found"));

        destination.setIsActive(isActive == null ? true : isActive);

        Destination updated = destinationRepository.save(destination);

        return mapToResponse(updated, lang);
    }

    // Admin API: xóa destination
    public void deleteDestination(Long id) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination not found"));

        destinationRepository.delete(destination);
    }

    private DestinationResponse mapToResponse(Destination destination, String lang) {
        boolean isFrench = "fr".equalsIgnoreCase(lang);

        Country country = destination.getCountry();

        String countryName = null;
        if (country != null) {
            countryName = isFrench ? country.getNameFr() : country.getNameEn();
        }

        return new DestinationResponse(
                destination.getId(),
                country != null ? country.getId() : null,
                countryName,
                destination.getRegion(),
                isFrench ? destination.getNameFr() : destination.getNameEn(),
                isFrench ? destination.getSlugFr() : destination.getSlugEn(),
                isFrench ? destination.getShortDescriptionFr() : destination.getShortDescriptionEn(),
                isFrench ? destination.getContentFr() : destination.getContentEn(),
                isFrench ? destination.getBestTimeToVisitFr() : destination.getBestTimeToVisitEn(),
                destination.getThumbnailUrl(),
                destination.getHeroImageUrl(),
                destination.getLatitude(),
                destination.getLongitude(),
                destination.getIsFeatured(),
                destination.getIsActive(),
                destination.getDisplayOrder()
        );
    }
}