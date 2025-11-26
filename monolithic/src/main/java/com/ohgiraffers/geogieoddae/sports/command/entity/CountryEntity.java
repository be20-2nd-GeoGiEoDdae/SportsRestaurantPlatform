package com.ohgiraffers.geogieoddae.sports.command.entity;

import com.ohgiraffers.geogieoddae.sports.command.entity.SportsEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "country")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_code")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_code")
    private SportsEntity sport;

    public void update(String name) {
        this.name = name;
    }
}
