package com.travel.repository;

import com.travel.entity.TourCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TourCollectionRepository extends JpaRepository<TourCollection, Long> {

    List<TourCollection> findByIsActiveTrueOrderByDisplayOrderAsc();

    List<TourCollection> findByTypeAndIsActiveTrueOrderByDisplayOrderAsc(String type);

    Optional<TourCollection> findBySlugEnAndIsActiveTrue(String slugEn);

    Optional<TourCollection> findBySlugFrAndIsActiveTrue(String slugFr);

    List<TourCollection> findAllByOrderByDisplayOrderAsc();

    List<TourCollection> findByTypeOrderByDisplayOrderAsc(String type);
}