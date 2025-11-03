package com.ohgiraffers.geogieoddae.report.command.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportTypeRequestDto {
    private String reportTypeCode;
    private String reportTypeType;
}
