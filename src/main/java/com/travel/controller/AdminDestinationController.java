package com.travel.controller;

import com.travel.dto.DestinationRequest;
import com.travel.dto.DestinationResponse;
import com.travel.service.DestinationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/destinations")
@CrossOrigin(origins = "*")
public class AdminDestinationController {

    private final DestinationService destinationService;

    public AdminDestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping
    public List<DestinationResponse> getAllDestinations(
            @RequestParam(required = false) String region,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return destinationService.getAllDestinationsForAdmin(region, lang);
    }

    @GetMapping("/{id}")
    public DestinationResponse getDestinationById(
            @PathVariable Long id,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return destinationService.getDestinationById(id, lang);
    }

    @PostMapping
    public DestinationResponse createDestination(
            @RequestBody DestinationRequest request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return destinationService.createDestination(request, lang);
    }

    @PutMapping("/{id}")
    public DestinationResponse updateDestination(
            @PathVariable Long id,
            @RequestBody DestinationRequest request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return destinationService.updateDestination(id, request, lang);
    }

    @PatchMapping("/{id}/status")
    public DestinationResponse updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return destinationService.updateDestinationStatus(id, request.get("isActive"), lang);
    }

    @DeleteMapping("/{id}")
    public String deleteDestination(@PathVariable Long id) {
        destinationService.deleteDestination(id);
        return "Delete destination successfully";
    }
}