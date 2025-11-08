package com.geogieoddae.mainservice.report.command.entity;

import com.geogieoddae.mainservice.restaurant.command.entity.restaurant.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_code")
    private Long reportCode;

    @Column(name = "report_contents", length = 1024)
    private String reportContents;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_status")
    private ReportStatus reportStatus;

    @ManyToOne
    @JoinColumn(name = "restaurant_code", nullable = false)
    private RestaurantEntity restaurant;

    @ManyToOne
    @JoinColumn(name = "report_type_code", nullable = false)
    private ReportTypeEntity reportType;

    private Long memberCode;
}
