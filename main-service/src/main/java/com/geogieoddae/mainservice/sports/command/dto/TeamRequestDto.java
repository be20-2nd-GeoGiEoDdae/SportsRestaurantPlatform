package com.geogieoddae.mainservice.sports.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequestDto {
    private String teamName;
    private String teamDescription;
    private Long sportCode;
}
