package com.ohgiraffers.geogieoddae.sports.command.entity;

import com.ohgiraffers.geogieoddae.sports.command.entity.LeagueEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_code")
    private Long teamCode;

    @Column(name = "team_name", nullable = false)
    private String teamName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "league_code")
    private LeagueEntity league;

    public void update(String name) {
        this.teamName = name;
    }

    public TeamEntity(String teamName, LeagueEntity league) {
        this.teamName = teamName;
        this.league = league;
    }
}
