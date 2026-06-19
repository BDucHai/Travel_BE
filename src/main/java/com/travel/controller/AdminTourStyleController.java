package com.travel.controller;

import com.travel.dto.TourStyleRequest;
import com.travel.dto.TourStyleResponse;
import com.travel.service.TourStyleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/tour-styles")
@CrossOrigin(origins = "*")
public class AdminTourStyleController {

    private final TourStyleService tourStyleService;

    public AdminTourStyleController(TourStyleService tourStyleService) {
        this.tourStyleService = tourStyleService;
    }

    @GetMapping
    public List<TourStyleResponse> getAllTourStyles(
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourStyleService.getAllTourStylesForAdmin(lang);
    }

    @GetMapping("/{id}")
    public TourStyleResponse getTourStyleById(
            @PathVariable Long id,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourStyleService.getTourStyleById(id, lang);
    }

    @PostMapping
    public TourStyleResponse createTourStyle(
            @RequestBody TourStyleRequest request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourStyleService.createTourStyle(request, lang);
    }

    @PutMapping("/{id}")
    public TourStyleResponse updateTourStyle(
            @PathVariable Long id,
            @RequestBody TourStyleRequest request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourStyleService.updateTourStyle(id, request, lang);
    }

    @PatchMapping("/{id}/status")
    public TourStyleResponse updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourStyleService.updateTourStyleStatus(id, request.get("isActive"), lang);
    }

    @DeleteMapping("/{id}")
    public String deleteTourStyle(@PathVariable Long id) {
        tourStyleService.deleteTourStyle(id);
        return "Delete tour style successfully";
    }
}