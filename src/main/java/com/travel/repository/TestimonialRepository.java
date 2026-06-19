package com.travel.repository;

import com.travel.entity.Testimonial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {

    long countByStatus(String status);

    // Method cũ giữ lại nếu chỗ khác đang dùng
    List<Testimonial> findByStatusOrderByCreatedAtDesc(String status);

    List<Testimonial> findAllByOrderByCreatedAtDesc();

    // Method mới dùng cho pagination
    Page<Testimonial> findByStatusOrderByCreatedAtDesc(
            String status,
            Pageable pageable
    );

    Page<Testimonial> findAllByOrderByCreatedAtDesc(
            Pageable pageable
    );
}