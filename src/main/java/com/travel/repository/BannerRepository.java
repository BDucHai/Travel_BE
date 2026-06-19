package com.travel.repository;

import com.travel.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner, Long> {

	long countByIsActiveTrue();
    List<Banner> findByPositionAndIsActiveTrueOrderByDisplayOrderAsc(String position);

    List<Banner> findAllByOrderByDisplayOrderAsc();
}