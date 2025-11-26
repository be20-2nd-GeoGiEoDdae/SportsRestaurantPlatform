package com.ohgiraffers.geogieoddae.sports.command.entity;

import com.ohgiraffers.geogieoddae.sports.command.entity.SportsEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "competition")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompetitionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competition_code")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sport_code")
    private SportsEntity sport;

    public void update(String name) {
        this.name = name;
    }
}
