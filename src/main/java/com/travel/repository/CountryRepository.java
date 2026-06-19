package com.travel.repository;

import com.travel.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findBySlugEn(String slugEn);

    Optional<Country> findBySlugFr(String slugFr);

    List<Country> findByIsActiveTrueOrderByIdAsc();

    List<Country> findAllByOrderByIdAsc();
}