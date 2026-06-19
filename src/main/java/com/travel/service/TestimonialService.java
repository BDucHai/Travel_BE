package com.travel.service;

import com.travel.dto.PaginationResponse;
import com.travel.dto.TestimonialImageRequest;
import com.travel.dto.TestimonialRequest;
import com.travel.dto.TestimonialResponse;
import com.travel.entity.Testimonial;
import com.travel.entity.TestimonialImage;
import com.travel.repository.TestimonialImageRepository;
import com.travel.repository.TestimonialRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestimonialService {

    private final TestimonialRepository testimonialRepository;
    private final TestimonialImageRepository testimonialImageRepository;

    public TestimonialService(
            TestimonialRepository testimonialRepository,
            TestimonialImageRepository testimonialImageRepository
    ) {
        this.testimonialRepository = testimonialRepository;
        this.testimonialImageRepository = testimonialImageRepository;
    }

    // Public API: chỉ lấy APPROVED + pagination
    public PaginationResponse<TestimonialResponse> getApprovedTestimonials(
            Integer page,
            Integer limit
    ) {
        int pageNumber = page == null || page < 0 ? 0 : page;
        int pageSize = limit == null || limit <= 0 ? 6 : limit;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Testimonial> testimonialPage =
                testimonialRepository.findByStatusOrderByCreatedAtDesc(
                        "APPROVED",
                        pageable
                );

        List<TestimonialResponse> data = testimonialPage.getContent()
                .stream()
                .map(this::mapToResponse)
                .toList();

        return new PaginationResponse<>(
                data,
                testimonialPage.getNumber(),
                testimonialPage.getSize(),
                testimonialPage.getTotalElements(),
                testimonialPage.getTotalPages(),
                testimonialPage.isFirst(),
                testimonialPage.isLast()
        );
    }

    // Method cũ giữ lại nếu cần dùng ở nơi khác
    public List<TestimonialResponse> getApprovedTestimonials() {
        return testimonialRepository.findByStatusOrderByCreatedAtDesc("APPROVED")
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Admin API: lấy tất cả hoặc filter status + pagination
    public PaginationResponse<TestimonialResponse> getTestimonialsForAdmin(
            String status,
            Integer page,
            Integer limit
    ) {
        int pageNumber = page == null || page < 0 ? 0 : page;
        int pageSize = limit == null || limit <= 0 ? 10 : limit;

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Testimonial> testimonialPage;

        if (status != null && !status.isBlank()) {
            testimonialPage = testimonialRepository.findByStatusOrderByCreatedAtDesc(
                    status.toUpperCase(),
                    pageable
            );
        } else {
            testimonialPage = testimonialRepository.findAllByOrderByCreatedAtDesc(pageable);
        }

        List<TestimonialResponse> data = testimonialPage.getContent()
                .stream()
                .map(this::mapToResponse)
                .toList();

        return new PaginationResponse<>(
                data,
                testimonialPage.getNumber(),
                testimonialPage.getSize(),
                testimonialPage.getTotalElements(),
                testimonialPage.getTotalPages(),
                testimonialPage.isFirst(),
                testimonialPage.isLast()
        );
    }

    // Method cũ giữ lại nếu chỗ khác đang gọi
    public List<TestimonialResponse> getAllTestimonialsForAdmin() {
        return testimonialRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public TestimonialResponse getTestimonialById(Long id) {
        Testimonial testimonial = testimonialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Testimonial not found"));

        return mapToResponse(testimonial);
    }

    public TestimonialResponse createTestimonial(TestimonialRequest request) {
        Testimonial testimonial = new Testimonial();

        testimonial.setName(request.getName());
        testimonial.setEmail(request.getEmail());
        testimonial.setAvatarUrl(request.getAvatarUrl());
        testimonial.setCountry(request.getCountry());
        testimonial.setRating(request.getRating());
        testimonial.setContent(request.getContent());
        testimonial.setStatus(
                request.getStatus() == null || request.getStatus().isBlank()
                        ? "PENDING"
                        : request.getStatus().toUpperCase()
        );

        Testimonial saved = testimonialRepository.save(testimonial);

        return mapToResponse(saved);
    }

    public TestimonialResponse updateTestimonial(Long id, TestimonialRequest request) {
        Testimonial testimonial = testimonialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Testimonial not found"));

        testimonial.setName(request.getName());
        testimonial.setEmail(request.getEmail());
        testimonial.setAvatarUrl(request.getAvatarUrl());
        testimonial.setCountry(request.getCountry());
        testimonial.setRating(request.getRating());
        testimonial.setContent(request.getContent());

        if (request.getStatus() != null && !request.getStatus().isBlank()) {
            testimonial.setStatus(request.getStatus().toUpperCase());
        }

        Testimonial updated = testimonialRepository.save(testimonial);

        return mapToResponse(updated);
    }

    public TestimonialResponse updateStatus(Long id, String status) {
        Testimonial testimonial = testimonialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Testimonial not found"));

        testimonial.setStatus(status.toUpperCase());

        Testimonial updated = testimonialRepository.save(testimonial);

        return mapToResponse(updated);
    }

    public TestimonialResponse approveTestimonial(Long id) {
        return updateStatus(id, "APPROVED");
    }

    public TestimonialResponse addImage(Long testimonialId, TestimonialImageRequest request) {
        Testimonial testimonial = testimonialRepository.findById(testimonialId)
                .orElseThrow(() -> new RuntimeException("Testimonial not found"));

        TestimonialImage image = new TestimonialImage();
        image.setTestimonial(testimonial);
        image.setImageUrl(request.getImageUrl());
        image.setDisplayOrder(request.getDisplayOrder() == null ? 0 : request.getDisplayOrder());

        testimonialImageRepository.save(image);

        return mapToResponse(testimonial);
    }

    public void deleteImage(Long imageId) {
        TestimonialImage image = testimonialImageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Testimonial image not found"));

        testimonialImageRepository.delete(image);
    }

    @Transactional
    public void deleteTestimonial(Long id) {
        Testimonial testimonial = testimonialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Testimonial not found"));

        testimonialImageRepository.deleteByTestimonialId(id);
        testimonialRepository.delete(testimonial);
    }

    private TestimonialResponse mapToResponse(Testimonial testimonial) {
        List<String> imageUrls = testimonialImageRepository
                .findByTestimonialIdOrderByDisplayOrderAsc(testimonial.getId())
                .stream()
                .map(TestimonialImage::getImageUrl)
                .toList();

        return new TestimonialResponse(
                testimonial.getId(),
                testimonial.getName(),
                testimonial.getEmail(),
                testimonial.getAvatarUrl(),
                testimonial.getCountry(),
                testimonial.getRating(),
                testimonial.getContent(),
                testimonial.getCreatedAt(),
                imageUrls
        );
    }
}