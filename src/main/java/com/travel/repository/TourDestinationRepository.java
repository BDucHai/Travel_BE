package com.travel.repository;

import com.travel.entity.TourDestination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourDestinationRepository extends JpaRepository<TourDestination, Long> {

    List<TourDestination> findByTourIdOrderByDisplayOrderAsc(Long tourId);

    void deleteByTourId(Long tourId);
}