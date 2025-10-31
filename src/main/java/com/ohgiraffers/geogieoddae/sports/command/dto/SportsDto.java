package com.ohgiraffers.geogieoddae.sports.command.dto;

import jakarta.persistence.Column;

public class SportsDto {

    @Column(name = "sport_name", nullable = false)
    private String sportName;

    @Column(name = "sport_decription")
    private String sportDescription;

}
