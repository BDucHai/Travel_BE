package com.travel.controller;

import com.travel.dto.CountryResponse;
import com.travel.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@CrossOrigin(origins = "*")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<CountryResponse> getAllCountries(
            @RequestParam(defaultValue = "en") String lang
    ) {
        return countryService.getAllCountries(lang);
    }
}