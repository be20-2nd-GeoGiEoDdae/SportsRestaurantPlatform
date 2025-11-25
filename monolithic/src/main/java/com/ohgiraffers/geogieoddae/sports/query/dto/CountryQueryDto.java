package com.ohgiraffers.geogieoddae.sports.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public class CountryQueryDto {
    private Long countryId;
    private String countryName;

    public Long getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }
}
