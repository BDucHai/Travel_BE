package com.travel.controller;

import com.travel.dto.TourResponse;
import com.travel.service.TourService;
import org.springframework.web.bind.annotation.*;
import com.travel.dto.PaginationResponse;

import java.util.List;

@RestController
@RequestMapping("/api/tours")
@CrossOrigin(origins = "*")
public class TourController {

    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping
    public PaginationResponse<TourResponse> getTours(
            @RequestParam(required = false) Integer duration,
            @RequestParam(required = false) String styleSlug,
            @RequestParam(required = false) String collectionSlug,
            @RequestParam(required = false) String destinationSlug,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "6") Integer limit,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourService.getTours(
                duration,
                styleSlug,
                collectionSlug,
                destinationSlug,
                page,
                limit,
                lang
        );
    }

    @GetMapping("/{slug}")
    public TourResponse getTourDetail(
            @PathVariable String slug,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourService.getTourDetail(slug, lang);
    }
}