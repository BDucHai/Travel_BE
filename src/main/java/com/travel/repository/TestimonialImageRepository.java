package com.travel.repository;

import com.travel.entity.TestimonialImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestimonialImageRepository extends JpaRepository<TestimonialImage, Long> {

    List<TestimonialImage> findByTestimonialIdOrderByDisplayOrderAsc(Long testimonialId);

    void deleteByTestimonialId(Long testimonialId);
}