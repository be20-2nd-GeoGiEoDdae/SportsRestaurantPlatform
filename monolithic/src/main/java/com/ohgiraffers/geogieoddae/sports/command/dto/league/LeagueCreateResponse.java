package com.ohgiraffers.geogieoddae.sports.command.dto.league;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeagueCreateResponse {
    private String name;
    private Long sportId;
    private Long countryId;
}