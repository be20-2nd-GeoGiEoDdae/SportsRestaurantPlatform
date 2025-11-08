package com.geogieoddae.mainservice.report.command.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "report_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportTypeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_type_code")
    private Long reportTypeCode;

    @Column(name = "report_type_type")
    private String reportTypeType;

    @OneToMany(mappedBy = "reportType")
    private List<ReportEntity> reports;
}
