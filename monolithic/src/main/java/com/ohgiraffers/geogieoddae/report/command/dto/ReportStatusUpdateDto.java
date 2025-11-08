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
public class ReportStatusUpdateDto {
    private ReportStatus reportStatus;
}
