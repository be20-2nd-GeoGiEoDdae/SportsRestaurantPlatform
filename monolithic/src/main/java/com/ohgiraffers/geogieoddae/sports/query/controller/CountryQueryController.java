package com.ohgiraffers.geogieoddae.sports.query.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.sports.query.service.CountryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sports/{sportId}/countries")
@RequiredArgsConstructor
public class CountryQueryController {

    private final CountryQueryService countryQueryService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getCountriesBySport(@PathVariable Long sportId) {
        return ResponseEntity.ok(ApiResponse.success(
                countryQueryService.findBySport(sportId)
        ));
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<ApiResponse<?>> getCountry(
            @PathVariable Long sportId,
            @PathVariable Long countryId
    ) {
        return ResponseEntity.ok(ApiResponse.success(
                countryQueryService.findOne(countryId)
        ));
    }
}
