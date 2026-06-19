package com.travel.repository;

import com.travel.entity.TourImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourImageRepository extends JpaRepository<TourImage, Long> {

    List<TourImage> findByTourIdOrderByDisplayOrderAsc(Long tourId);

    void deleteByTourId(Long tourId);
}