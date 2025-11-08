package com.ohgiraffers.geogieoddae.report.command.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequestDto {
    private String reportContents;   // 신고 내용
    private Long restaurantCode;
    private Long reportTypeCode;
}
