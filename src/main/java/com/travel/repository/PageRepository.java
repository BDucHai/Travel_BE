package com.travel.repository;

import com.travel.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PageRepository extends JpaRepository<Page, Long> {

    Optional<Page> findByPageKeyAndStatus(String pageKey, String status);

    Optional<Page> findBySlugEnAndStatus(String slugEn, String status);

    Optional<Page> findBySlugFrAndStatus(String slugFr, String status);

    List<Page> findAllByOrderByCreatedAtDesc();
}