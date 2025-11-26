package com.ohgiraffers.geogieoddae.sports.command.dto.league;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeagueCreateRequest {
    private Long sportId;
    private Long countryId;
    private String name;
}