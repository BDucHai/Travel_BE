package com.travel.controller;

import com.travel.service.TourService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/tour-images")
@CrossOrigin(origins = "*")
public class AdminTourImageController {

    private final TourService tourService;

    public AdminTourImageController(TourService tourService) {
        this.tourService = tourService;
    }

    @DeleteMapping("/{imageId}")
    public String deleteImage(@PathVariable Long imageId) {
        tourService.deleteImage(imageId);
        return "Delete tour image successfully";
    }
}