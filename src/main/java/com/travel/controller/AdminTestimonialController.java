package com.travel.controller;

import com.travel.dto.PaginationResponse;
import com.travel.dto.TestimonialImageRequest;
import com.travel.dto.TestimonialRequest;
import com.travel.dto.TestimonialResponse;
import com.travel.service.TestimonialService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/testimonials")
@CrossOrigin(origins = "*")
public class AdminTestimonialController {

    private final TestimonialService testimonialService;

    public AdminTestimonialController(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }

    @GetMapping
    public PaginationResponse<TestimonialResponse> getTestimonials(
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer limit
    ) {
        return testimonialService.getTestimonialsForAdmin(status, page, limit);
    }

    @GetMapping("/{id}")
    public TestimonialResponse getTestimonialById(@PathVariable Long id) {
        return testimonialService.getTestimonialById(id);
    }

    // @PostMapping
    // public TestimonialResponse createTestimonial(@RequestBody TestimonialRequest request) {
    //     return testimonialService.createTestimonial(request);
    // }

    @PutMapping("/{id}")
    public TestimonialResponse updateTestimonial(
            @PathVariable Long id,
            @RequestBody TestimonialRequest request
    ) {
        return testimonialService.updateTestimonial(id, request);
    }

    @PatchMapping("/{id}/status")
    public TestimonialResponse updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> request
    ) {
        return testimonialService.updateStatus(id, request.get("status"));
    }

    @PutMapping("/{id}/approve")
    public TestimonialResponse approveTestimonial(@PathVariable Long id) {
        return testimonialService.approveTestimonial(id);
    }

    // @PostMapping("/{id}/images")
    // public TestimonialResponse addImage(
    //         @PathVariable Long id,
    //         @RequestBody TestimonialImageRequest request
    // ) {
    //     return testimonialService.addImage(id, request);
    // }

    @DeleteMapping("/{id}")
    public String deleteTestimonial(@PathVariable Long id) {
        testimonialService.deleteTestimonial(id);
        return "Delete testimonial successfully";
    }
}