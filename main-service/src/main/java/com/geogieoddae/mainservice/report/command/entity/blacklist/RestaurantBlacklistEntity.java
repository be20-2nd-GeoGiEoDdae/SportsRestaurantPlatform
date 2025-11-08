package com.geogieoddae.mainservice.report.command.entity.blacklist;

import com.geogieoddae.mainservice.global.common.entity.BaseTimeEntity;
import com.geogieoddae.mainservice.report.command.entity.ReportEntity;
import com.geogieoddae.mainservice.restaurant.command.entity.restaurant.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;


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
