package com.geogieoddae.mainservice.report.command.dto;

import com.geogieoddae.mainservice.report.command.entity.ReportStatus;
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
