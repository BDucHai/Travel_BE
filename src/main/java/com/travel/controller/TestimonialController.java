package com.travel.controller;

import com.travel.dto.PaginationResponse;
import com.travel.dto.TestimonialResponse;
import com.travel.dto.TestimonialRequest;
import com.travel.dto.TestimonialImageRequest;
import com.travel.service.TestimonialService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/testimonials")
@CrossOrigin(origins = "*")
public class TestimonialController {

    private final TestimonialService testimonialService;

    public TestimonialController(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }

    @GetMapping
    public PaginationResponse<TestimonialResponse> getApprovedTestimonials(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "6") Integer limit
    ) {
        return testimonialService.getApprovedTestimonials(page, limit);
    }

    @PostMapping
    public TestimonialResponse createTestimonial(@RequestBody TestimonialRequest request) {
        return testimonialService.createTestimonial(request);
    }

    @PostMapping("/{id}/images")
    public TestimonialResponse addImage(
            @PathVariable Long id,
            @RequestBody TestimonialImageRequest request
    ) {
        return testimonialService.addImage(id, request);
    }
}