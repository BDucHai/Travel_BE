package com.travel.controller;

import com.travel.dto.TourCollectionRequest;
import com.travel.dto.TourCollectionResponse;
import com.travel.service.TourCollectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/tour-collections")
@CrossOrigin(origins = "*")
public class AdminTourCollectionController {

    private final TourCollectionService tourCollectionService;

    public AdminTourCollectionController(TourCollectionService tourCollectionService) {
        this.tourCollectionService = tourCollectionService;
    }

    @GetMapping
    public List<TourCollectionResponse> getAllTourCollections(
            @RequestParam(required = false) String type,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourCollectionService.getAllTourCollectionsForAdmin(type, lang);
    }

    @GetMapping("/{id}")
    public TourCollectionResponse getTourCollectionById(
            @PathVariable Long id,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourCollectionService.getTourCollectionById(id, lang);
    }

    @PostMapping
    public TourCollectionResponse createTourCollection(
            @RequestBody TourCollectionRequest request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourCollectionService.createTourCollection(request, lang);
    }

    @PutMapping("/{id}")
    public TourCollectionResponse updateTourCollection(
            @PathVariable Long id,
            @RequestBody TourCollectionRequest request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourCollectionService.updateTourCollection(id, request, lang);
    }

    @PatchMapping("/{id}/status")
    public TourCollectionResponse updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourCollectionService.updateTourCollectionStatus(id, request.get("isActive"), lang);
    }

    @DeleteMapping("/{id}")
    public String deleteTourCollection(@PathVariable Long id) {
        tourCollectionService.deleteTourCollection(id);
        return "Delete tour collection successfully";
    }
}