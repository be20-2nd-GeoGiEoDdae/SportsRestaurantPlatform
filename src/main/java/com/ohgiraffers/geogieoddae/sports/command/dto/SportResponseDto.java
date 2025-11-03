package com.ohgiraffers.geogieoddae.sports.command.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SportResponseDto {
    private Long sportCode;
    private String sportName;
    private String sportDescription;
}
