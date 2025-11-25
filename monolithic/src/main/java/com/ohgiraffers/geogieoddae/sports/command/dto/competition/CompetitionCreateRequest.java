package com.ohgiraffers.geogieoddae.sports.command.dto.competition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompetitionCreateRequest {
    private Long sportId;
    private String name;
}
