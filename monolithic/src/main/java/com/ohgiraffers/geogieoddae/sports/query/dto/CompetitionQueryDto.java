package com.ohgiraffers.geogieoddae.sports.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CompetitionQueryDto {
    private Long competitionId;
    private String competitionName;
}
