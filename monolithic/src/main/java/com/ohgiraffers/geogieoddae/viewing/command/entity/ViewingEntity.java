package com.ohgiraffers.geogieoddae.viewing.command.entity;

import com.ohgiraffers.geogieoddae.global.common.entity.BaseTimeEntity;
import com.ohgiraffers.geogieoddae.pay.command.entity.ViewingPayEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.CompetitionEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.LeagueEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.SportsEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.TeamEntity;
import com.ohgiraffers.geogieoddae.viewing.command.entity.ViewingStatus;
import com.ohgiraffers.geogieoddae.viewing.command.entity.ViewingUserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "viewing")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewingEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viewing_code")
    private Long viewingCode;

    private String viewingTitle;
    private String viewingBody;
    private LocalDateTime viewingAt;
    private Integer viewingTotalDeposit;

    @Enumerated(EnumType.STRING)
    private ViewingStatus viewingStatus;

    private Integer viewingMinNum;
    private Integer viewingMaxNum;

    @ManyToOne
    @JoinColumn(name = "restaurant_code", nullable = false)
    private RestaurantEntity restaurant;

    @ManyToOne
    @JoinColumn(name = "sport_code", nullable = false)
    private SportsEntity sport;

    @ManyToOne
    @JoinColumn(name = "team_code", nullable = false)
    private TeamEntity team;

    // 선택적: 리그 기반 관람
    @ManyToOne
    @JoinColumn(name = "league_id", nullable = true)
    private LeagueEntity league;

    // 선택적: 국제 대회 기반 관람
    @ManyToOne
    @JoinColumn(name = "competition_id", nullable = true)
    private CompetitionEntity competition;

    @OneToMany(mappedBy = "viewing")
    private List<ViewingUserEntity> viewingUsers;

    @OneToMany(mappedBy = "viewing")
    private List<ViewingPayEntity> viewingPays;
}
