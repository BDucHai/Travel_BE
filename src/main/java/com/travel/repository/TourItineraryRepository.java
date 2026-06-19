package com.travel.repository;

import com.travel.entity.TourItinerary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourItineraryRepository extends JpaRepository<TourItinerary, Long> {

    List<TourItinerary> findByTourIdOrderByDisplayOrderAsc(Long tourId);

    void deleteByTourId(Long tourId);
}