package com.travel.repository;

import com.travel.entity.TourStyle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TourStyleRepository extends JpaRepository<TourStyle, Long> {

    List<TourStyle> findByIsActiveTrueOrderByDisplayOrderAsc();

    Optional<TourStyle> findBySlugEnAndIsActiveTrue(String slugEn);

    Optional<TourStyle> findBySlugFrAndIsActiveTrue(String slugFr);

    List<TourStyle> findAllByOrderByDisplayOrderAsc();
}