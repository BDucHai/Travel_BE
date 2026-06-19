package com.travel.controller;

import com.travel.dto.DestinationResponse;
import com.travel.service.DestinationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
@CrossOrigin(origins = "*")
public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping
    public List<DestinationResponse> getDestinations(
            @RequestParam(required = false) String region,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return destinationService.getDestinations(region, lang);
    }

    @GetMapping("/{slug}")
    public DestinationResponse getDestinationDetail(
            @PathVariable String slug,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return destinationService.getDestinationDetail(slug, lang);
    }
}