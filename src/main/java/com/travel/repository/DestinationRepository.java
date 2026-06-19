package com.travel.repository;

import com.travel.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

    List<Destination> findByIsActiveTrueOrderByDisplayOrderAsc();

    List<Destination> findByRegionAndIsActiveTrueOrderByDisplayOrderAsc(String region);

    Optional<Destination> findBySlugEnAndIsActiveTrue(String slugEn);

    Optional<Destination> findBySlugFrAndIsActiveTrue(String slugFr);

    List<Destination> findAllByOrderByDisplayOrderAsc();

    List<Destination> findByRegionOrderByDisplayOrderAsc(String region);
    
}