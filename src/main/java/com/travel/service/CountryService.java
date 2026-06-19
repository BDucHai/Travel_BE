package com.travel.service;

import com.travel.dto.CountryRequest;
import com.travel.dto.CountryResponse;
import com.travel.entity.Country;
import com.travel.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    // Public API: chỉ lấy country active
    public List<CountryResponse> getAllCountries(String lang) {
        return countryRepository.findByIsActiveTrueOrderByIdAsc()
                .stream()
                .map(country -> mapToResponse(country, lang))
                .toList();
    }

    // Admin API: lấy tất cả country
    public List<CountryResponse> getAllCountriesForAdmin(String lang) {
        return countryRepository.findAllByOrderByIdAsc()
                .stream()
                .map(country -> mapToResponse(country, lang))
                .toList();
    }

    // Admin API: xem chi tiết country theo id
    public CountryResponse getCountryById(Long id, String lang) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        return mapToResponse(country, lang);
    }

    // Admin API: tạo country
    public CountryResponse createCountry(CountryRequest request, String lang) {
        Country country = new Country();

        country.setCode(request.getCode());

        country.setNameEn(request.getNameEn());
        country.setNameFr(request.getNameFr());

        country.setSlugEn(request.getSlugEn());
        country.setSlugFr(request.getSlugFr());

        country.setDescriptionEn(request.getDescriptionEn());
        country.setDescriptionFr(request.getDescriptionFr());

        country.setThumbnailUrl(request.getThumbnailUrl());
        country.setIsActive(request.getIsActive() == null ? true : request.getIsActive());

        Country saved = countryRepository.save(country);

        return mapToResponse(saved, lang);
    }

    // Admin API: update country
    public CountryResponse updateCountry(Long id, CountryRequest request, String lang) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        country.setCode(request.getCode());

        country.setNameEn(request.getNameEn());
        country.setNameFr(request.getNameFr());

        country.setSlugEn(request.getSlugEn());
        country.setSlugFr(request.getSlugFr());

        country.setDescriptionEn(request.getDescriptionEn());
        country.setDescriptionFr(request.getDescriptionFr());

        country.setThumbnailUrl(request.getThumbnailUrl());

        if (request.getIsActive() != null) {
            country.setIsActive(request.getIsActive());
        }

        Country updated = countryRepository.save(country);

        return mapToResponse(updated, lang);
    }

    // Admin API: bật/tắt country
    public CountryResponse updateCountryStatus(Long id, Boolean isActive, String lang) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        country.setIsActive(isActive == null ? true : isActive);

        Country updated = countryRepository.save(country);

        return mapToResponse(updated, lang);
    }

    // Admin API: xóa country
    public void deleteCountry(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        countryRepository.delete(country);
    }

    private CountryResponse mapToResponse(Country country, String lang) {
        boolean isFrench = "fr".equalsIgnoreCase(lang);

        return new CountryResponse(
                country.getId(),
                country.getCode(),
                isFrench ? country.getNameFr() : country.getNameEn(),
                isFrench ? country.getSlugFr() : country.getSlugEn(),
                isFrench ? country.getDescriptionFr() : country.getDescriptionEn(),
                country.getThumbnailUrl(),
                country.getIsActive()
        );
    }
}