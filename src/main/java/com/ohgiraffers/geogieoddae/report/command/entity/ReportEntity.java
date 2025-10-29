package com.ohgiraffers.geogieoddae.report.command.entity;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportEntity {

    @Id
    @Column(name = "report_code")
    private Long reportCode;

    @Column(name = "report_contents", length = 1024)
    private String reportContents;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_status", columnDefinition = "ENUM('승인','보류','미승인')")
    private ReportStatus reportStatus;

    @Column(name = "report_completed_at")
    private LocalDateTime reportCompletedAt;

    @Column(name = "report_created_at")
    private LocalDateTime reportCreatedAt;

    @ManyToOne
    @JoinColumn(name = "restaurant_code", nullable = false)
    private RestaurantEntity restaurant;

    @ManyToOne
    @JoinColumn(name = "report_type_code", nullable = false)
    private ReportTypeEntity reportType;

    @ManyToOne
    @JoinColumn(name = "user_code", nullable = false)
    private UserEntity member;
}
