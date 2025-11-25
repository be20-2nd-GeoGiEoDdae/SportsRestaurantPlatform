package com.ohgiraffers.geogieoddae.sports.command.dto.country;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryCreateRequest {
    private Long sportId;
    private String name;
}
