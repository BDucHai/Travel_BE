package com.travel.controller;

import com.travel.dto.TourStyleResponse;
import com.travel.service.TourStyleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tour-styles")
@CrossOrigin(origins = "*")
public class TourStyleController {

    private final TourStyleService tourStyleService;

    public TourStyleController(TourStyleService tourStyleService) {
        this.tourStyleService = tourStyleService;
    }

    @GetMapping
    public List<TourStyleResponse> getAllTourStyles(
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourStyleService.getAllTourStyles(lang);
    }

    @GetMapping("/{slug}")
    public TourStyleResponse getTourStyleDetail(
            @PathVariable String slug,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourStyleService.getTourStyleDetail(slug, lang);
    }
}