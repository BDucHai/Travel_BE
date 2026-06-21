package com.travel.controller;

import com.travel.dto.*;
import com.travel.service.TourService;
import org.springframework.web.bind.annotation.*;
import com.travel.dto.AdminTourResponse;
import com.travel.dto.TourResponse;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/tours")
@CrossOrigin(origins = "*")
public class AdminTourController {

    private final TourService tourService;
    private final ObjectMapper objectMapper;

    public AdminTourController(
            TourService tourService,
            ObjectMapper objectMapper
    ) {
        this.tourService = tourService;
        this.objectMapper = objectMapper;
    }
    
    @PostMapping("/with-images")
    public TourResponse createTourWithImages(
            @RequestParam("data") String dataJson,
            @RequestParam(value = "featuredImage", required = false) MultipartFile featuredImage,
            @RequestParam(value = "images", required = false) MultipartFile[] images,
            @RequestParam(value = "itineraryImages", required = false) MultipartFile[] itineraryImages,
            @RequestParam(defaultValue = "en") String lang
    ) throws Exception {
        TourRequest request = objectMapper.readValue(dataJson, TourRequest.class);

        return tourService.createTourWithImages(
                request,
                featuredImage,
                images,
                itineraryImages,
                lang
        );
    }

    @PutMapping(value = "/update/with-images/{id}")
    public TourResponse updateTourWithImages(
            @PathVariable Long id,
            @RequestPart("data") TourRequest request,
            @RequestPart(value = "itineraryImages", required = false) MultipartFile[] itineraryImages,
            @RequestPart(value = "featuredImage", required = false) MultipartFile featuredImage,
            @RequestPart(value = "images", required = false) MultipartFile[] images,
            @RequestParam String lang
    ) {
        return 
                tourService.updateTourWithImages(id, request, featuredImage, images, itineraryImages, lang);
    }

    @GetMapping
    public PaginationResponse<AdminTourResponse> getAllTours(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "") String titleEn
    ) {
        return tourService.getAllToursForAdmin(page, limit, titleEn);
    }

    @GetMapping("/{id}")
    public AdminTourResponse getTourById(@PathVariable Long id) {
        return tourService.getTourById(id);
    }

    @PostMapping
    public TourResponse createTour(
            @RequestBody TourRequest request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourService.createTour(request, lang);
    }

    @PutMapping("/{id}")
    public TourResponse updateTour(
            @PathVariable Long id,
            @RequestBody TourRequest request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourService.updateTour(id, request, lang);
    }

    @PatchMapping("/{id}/status")
    public TourResponse updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourService.updateTourStatus(id, request.get("status"), lang);
    }

    @PostMapping("/{id}/images")
    public TourResponse addImage(
            @PathVariable Long id,
            @RequestBody TourImageRequest request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourService.addImage(id, request, lang);
    }

    @PostMapping("/{id}/destinations")
    public TourResponse addDestination(
            @PathVariable Long id,
            @RequestBody TourDestinationRequest request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourService.addDestination(id, request, lang);
    }

    @PutMapping("/{id}/styles")
    public TourResponse updateStyles(
            @PathVariable Long id,
            @RequestBody IdsRequest request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourService.updateStyles(id, request.getIds(), lang);
    }

    @PutMapping("/{id}/collections")
    public TourResponse updateCollections(
            @PathVariable Long id,
            @RequestBody IdsRequest request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return tourService.updateCollections(id, request.getIds(), lang);
    }

    @DeleteMapping("/{id}")
    public String deleteTour(@PathVariable Long id) {
        tourService.deleteTour(id);
        return "Delete tour successfully";
    }
}