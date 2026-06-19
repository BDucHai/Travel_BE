package com.travel.controller;

import com.travel.dto.TourCollectionResponse;
import com.travel.service.TourCollectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tour-collections")
@CrossOrigin(origins = "*")
public class TourCollectionController {

    private final TourCollectionService tourCollectionService;

    public TourCollectionController(TourCollectionService tourCollectionService) {
        this.tourCollectionService = tourCollectionService;
    }

    @GetMapping
    public List<TourCollectionResponse> getTourCollections(
            @RequestParam(required = false) String type,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourCollectionService.getTourCollections(type, lang);
    }

    @GetMapping("/{slug}")
    public TourCollectionResponse getTourCollectionDetail(
            @PathVariable String slug,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourCollectionService.getTourCollectionDetail(slug, lang);
    }
}