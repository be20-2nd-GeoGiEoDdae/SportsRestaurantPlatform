package com.ohgiraffers.geogieoddae.report.query.dto;

import com.ohgiraffers.geogieoddae.report.command.entity.ReportStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportQueryDto {
    private Long reportCode;
    private String reportContents;
    private ReportStatus reportStatus;
    private Long restaurantCode;
    private String restaurantName;
    private String reportTypeCode;
    private String reportTypeType;
    private Long userCode;
    private String userName;
    private Integer reportCount;   // ✅ 가게별 신고 누적 횟수
}
