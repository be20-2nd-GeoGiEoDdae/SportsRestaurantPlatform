package com.ohgiraffers.geogieoddae.sports.command.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.global.common.repository.ApiRepsitory;
import com.ohgiraffers.geogieoddae.sports.command.dto.country.CountryCreateRequest;
import com.ohgiraffers.geogieoddae.sports.command.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/sports/countries")
@RequiredArgsConstructor
public class CountryCommandController {

    private final CountryService countryService;


    @PostMapping
    public ResponseEntity<ApiResponse<?>> createCountry(@RequestBody CountryCreateRequest request) {
        countryService.createCountry(request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PutMapping("/{CountryCode}")
    public ResponseEntity<ApiResponse<?>> updateCountry(
            @PathVariable Long CountryCode,
            @RequestBody CountryCreateRequest request) {
        countryService.updateCountry(CountryCode, request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @DeleteMapping("/{CountryCode}")
    public ResponseEntity<ApiResponse<?>> deleteCountry(@PathVariable Long CountryCode) {
        countryService.deleteCountry(CountryCode);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}

