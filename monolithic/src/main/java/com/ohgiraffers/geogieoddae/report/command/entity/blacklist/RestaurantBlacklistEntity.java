package com.ohgiraffers.geogieoddae.report.command.entity.blacklist;

import com.ohgiraffers.geogieoddae.global.common.entity.BaseTimeEntity;
import com.ohgiraffers.geogieoddae.report.command.entity.ReportEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "restaurant_blacklist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantBlacklistEntity extends BaseTimeEntity {

    @Id
    @Column(name = "report_code")
    private Long reportCode;

    @Column(name = "restaurant_blacklist_name", nullable = false)
    private String restaurantBlacklistName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_code")
    private RestaurantEntity restaurant;


    @ManyToOne
    @JoinColumn(name = "report_code", insertable = false, updatable = false)
    private ReportEntity report;
}
