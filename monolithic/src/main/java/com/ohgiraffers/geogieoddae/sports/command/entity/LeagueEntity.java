package com.ohgiraffers.geogieoddae.sports.command.entity;

import com.ohgiraffers.geogieoddae.sports.command.entity.CountryEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.SportsEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "league")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class LeagueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "league_code")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_code")
    private SportsEntity sport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_code")
    private CountryEntity country;
}

