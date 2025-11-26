package com.ohgiraffers.geogieoddae.sports.command.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponseDto {
    private Long id;
    private String name;
    private Long leagueId;
}
