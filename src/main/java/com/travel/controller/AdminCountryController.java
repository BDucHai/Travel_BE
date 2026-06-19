package com.travel.controller;

import com.travel.dto.CountryRequest;
import com.travel.dto.CountryResponse;
import com.travel.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/countries")
@CrossOrigin(origins = "*")
public class AdminCountryController {

    private final CountryService countryService;

    public AdminCountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<CountryResponse> getAllCountries(
            @RequestParam(defaultValue = "en") String lang
    ) {
        return countryService.getAllCountriesForAdmin(lang);
    }

    @GetMapping("/{id}")
    public CountryResponse getCountryById(
            @PathVariable Long id,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return countryService.getCountryById(id, lang);
    }

    @PostMapping
    public CountryResponse createCountry(
            @RequestBody CountryRequest request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return countryService.createCountry(request, lang);
    }

    @PutMapping("/{id}")
    public CountryResponse updateCountry(
            @PathVariable Long id,
            @RequestBody CountryRequest request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return countryService.updateCountry(id, request, lang);
    }

    @PatchMapping("/{id}/status")
    public CountryResponse updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> request,
            @RequestParam(defaultValue = "en") String lang
    ) {
        return countryService.updateCountryStatus(id, request.get("isActive"), lang);
    }

    @DeleteMapping("/{id}")
    public String deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return "Delete country successfully";
    }
}