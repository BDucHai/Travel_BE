package com.travel.service;

import com.travel.dto.MegaMenuResponse;
import com.travel.dto.MenuItemResponse;
import com.travel.entity.Destination;
import com.travel.entity.TourCollection;
import com.travel.entity.TourStyle;
import com.travel.repository.DestinationRepository;
import com.travel.repository.TourCollectionRepository;
import com.travel.repository.TourRepository;
import com.travel.repository.TourStyleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayoutService {

    private final TourRepository tourRepository;
    private final TourStyleRepository tourStyleRepository;
    private final TourCollectionRepository tourCollectionRepository;
    private final DestinationRepository destinationRepository;

    public LayoutService(
            TourRepository tourRepository,
            TourStyleRepository tourStyleRepository,
            TourCollectionRepository tourCollectionRepository,
            DestinationRepository destinationRepository
    ) {
        this.tourRepository = tourRepository;
        this.tourStyleRepository = tourStyleRepository;
        this.tourCollectionRepository = tourCollectionRepository;
        this.destinationRepository = destinationRepository;
    }

    public MegaMenuResponse getMegaMenu(String lang) {
        boolean isFrench = "fr".equalsIgnoreCase(lang);

        List<MenuItemResponse> durationItems = tourRepository.findDistinctPublishedDurations()
                .stream()
                .map(duration -> new MenuItemResponse(
                        null,
                        buildDurationLabel(duration, isFrench),
                        null,
                        duration,
                        "/tours?duration=" + duration
                ))
                .toList();

        List<MenuItemResponse> styleItems = tourStyleRepository.findByIsActiveTrueOrderByDisplayOrderAsc()
                .stream()
                .map(style -> mapStyle(style, isFrench))
                .toList();

        List<MenuItemResponse> combinedItems = tourCollectionRepository
                .findByTypeAndIsActiveTrueOrderByDisplayOrderAsc("COMBINED")
                .stream()
                .map(collection -> mapCollection(collection, isFrench))
                .toList();

        List<MenuItemResponse> northItems = destinationRepository
                .findByRegionAndIsActiveTrueOrderByDisplayOrderAsc("NORTH")
                .stream()
                .map(destination -> mapDestination(destination, isFrench))
                .toList();

        List<MenuItemResponse> centralItems = destinationRepository
                .findByRegionAndIsActiveTrueOrderByDisplayOrderAsc("CENTRAL")
                .stream()
                .map(destination -> mapDestination(destination, isFrench))
                .toList();

        List<MenuItemResponse> southItems = destinationRepository
                .findByRegionAndIsActiveTrueOrderByDisplayOrderAsc("SOUTH")
                .stream()
                .map(destination -> mapDestination(destination, isFrench))
                .toList();

        MegaMenuResponse.VietnamTourMenu vietnamTourMenu =
                new MegaMenuResponse.VietnamTourMenu(
                        durationItems,
                        styleItems,
                        combinedItems
                );

        MegaMenuResponse.TravelInformationMenu travelInformationMenu =
                new MegaMenuResponse.TravelInformationMenu(
                        northItems,
                        centralItems,
                        southItems
                );

        return new MegaMenuResponse(vietnamTourMenu, travelInformationMenu);
    }

    private String buildDurationLabel(Integer duration, boolean isFrench) {
        if (duration == null) {
            return null;
        }

        if (isFrench) {
            return duration + " jours";
        }

        return duration + " Days";
    }

    private MenuItemResponse mapStyle(TourStyle style, boolean isFrench) {
        String label = isFrench ? style.getNameFr() : style.getNameEn();
        String slug = isFrench ? style.getSlugFr() : style.getSlugEn();

        return new MenuItemResponse(
                style.getId(),
                label,
                slug,
                null,
                "/tours?styleSlug=" + slug
        );
    }

    private MenuItemResponse mapCollection(TourCollection collection, boolean isFrench) {
        String label = isFrench ? collection.getNameFr() : collection.getNameEn();
        String slug = isFrench ? collection.getSlugFr() : collection.getSlugEn();

        return new MenuItemResponse(
                collection.getId(),
                label,
                slug,
                null,
                "/tours?collectionSlug=" + slug
        );
    }

    private MenuItemResponse mapDestination(Destination destination, boolean isFrench) {
        String label = isFrench ? destination.getNameFr() : destination.getNameEn();
        String slug = isFrench ? destination.getSlugFr() : destination.getSlugEn();

        return new MenuItemResponse(
                destination.getId(),
                label,
                slug,
                null,
                "/destinations/" + slug
        );
    }
}