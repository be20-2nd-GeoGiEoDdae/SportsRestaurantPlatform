package com.ohgiraffers.geogieoddae.sports.command.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SportsResponseDto {
    private Long sportCode;
    private String sportName;
}
