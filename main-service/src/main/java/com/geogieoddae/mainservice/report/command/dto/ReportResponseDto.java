package com.geogieoddae.mainservice.report.command.dto;

import com.geogieoddae.mainservice.report.command.entity.ReportEntity;
import com.geogieoddae.mainservice.report.command.entity.ReportStatus;
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

//    public static ReportResponseDto from(ReportEntity entity) {
//        return ReportResponseDto.builder()
//                .reportCode(entity.getReportCode())
//                .reportStatus(entity.getReportStatus())
//                .restaurantCode(entity.getRestaurant().getRestaurantCode())
//                .userCode(entity.getMemberCode()
//                .reportTypeCode(entity.getReportType().getReportTypeCode())
//                .build();
    }









