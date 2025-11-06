package com.ohgiraffers.geogieoddae.report.command.dto;

import com.ohgiraffers.geogieoddae.report.command.entity.ReportEntity;
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
    private String reportContents;   // 신고 내용
    private ReportStatus reportStatus;
    private Long restaurantCode;
    private Long reportTypeCode;
    private Long userCode;

    public static ReportResponseDto from(ReportEntity entity) {
        return ReportResponseDto.builder()
                .reportCode(entity.getReportCode())
                .reportStatus(entity.getReportStatus())
                .restaurantCode(entity.getRestaurant().getRestaurantCode())
                .userCode(entity.getMember().getUserCode())
                .reportTypeCode(entity.getReportType().getReportTypeCode())
                .build();
    }








}
