package com.ohgiraffers.geogieoddae.viewing.command.entity;

import com.ohgiraffers.geogieoddae.global.common.entity.BaseTimeEntity;
import com.ohgiraffers.geogieoddae.pay.command.entity.ViewingPayEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.SportEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.TeamEntity;
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

    @Id
    @Column(name = "viewing_code")
    private Long viewingCode;

    @Column(name = "viewing_title", nullable = false)
    private String viewingTitle;

    @Column(name = "viewing_body", nullable = false)
    private String viewingBody;

    @Column(name = "viewing_at", nullable = false)
    private LocalDateTime viewingAt;

    @Column(name = "viewing_total_deposit", nullable = false)
    private Integer viewingTotalDeposit;

    @Enumerated(EnumType.STRING)
    @Column(name = "viewing_status", columnDefinition = "ENUM('진행','마감','삭제')")
    private ViewingStatus viewingStatus;

    @Column(name = "viewing_min_num")
    private Integer viewingMinNum;

    @Column(name = "viewing_max_num", nullable = false)
    private Integer viewingMaxNum;

    @ManyToOne
    @JoinColumn(name = "restaurant_code", nullable = false)
    private RestaurantEntity restaurant;

    @ManyToOne
    @JoinColumn(name = "sport_code", nullable = false)
    private SportEntity sport;

    @ManyToOne
    @JoinColumn(name = "team_code", nullable = false)
    private TeamEntity team;

    @OneToMany(mappedBy = "viewing")
    private List<ViewingUserEntity> viewingUsers;

    @OneToMany(mappedBy = "viewing")
    private List<ViewingPayEntity> viewingPays;
}
