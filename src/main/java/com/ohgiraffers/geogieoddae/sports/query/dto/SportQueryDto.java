package com.ohgiraffers.geogieoddae.sports.query.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SportQueryDto {
    private String sportName;
    private String sportDescription;
}
