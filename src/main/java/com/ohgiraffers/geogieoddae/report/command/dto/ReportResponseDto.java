package com.ohgiraffers.geogieoddae.report.command.dto;

import com.ohgiraffers.geogieoddae.report.command.entity.ReportStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponseDto {
    private Long reportCode;
    private String reportTitle;      // 신고 제목
    private String reportContents;   // 신고 내용
    private ReportStatus reportStatus;
    private Long restaurantCode;
    private String restaurantName;
    private String reportTypeCode;
    private String reportTypeType;
    private Long userCode;
}
