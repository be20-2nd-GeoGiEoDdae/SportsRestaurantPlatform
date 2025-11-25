package com.ohgiraffers.geogieoddae.sports.command.dto.country;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryCreateResponse {
    private Long id;
    private String name;
    private Long sportId;
}
