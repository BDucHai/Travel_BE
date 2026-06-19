package com.travel.controller;

import com.travel.service.TestimonialService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/testimonial-images")
@CrossOrigin(origins = "*")
public class AdminTestimonialImageController {

    private final TestimonialService testimonialService;

    public AdminTestimonialImageController(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }

    @DeleteMapping("/{imageId}")
    public String deleteImage(@PathVariable Long imageId) {
        testimonialService.deleteImage(imageId);
        return "Delete testimonial image successfully";
    }
}