package com.travel.controller;

import com.travel.service.TourService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/tour-destinations")
@CrossOrigin(origins = "*")
public class AdminTourDestinationController {

    private final TourService tourService;

    public AdminTourDestinationController(TourService tourService) {
        this.tourService = tourService;
    }

    @DeleteMapping("/{tourDestinationId}")
    public String deleteTourDestination(@PathVariable Long tourDestinationId) {
        tourService.deleteTourDestination(tourDestinationId);
        return "Delete tour destination successfully";
    }
}