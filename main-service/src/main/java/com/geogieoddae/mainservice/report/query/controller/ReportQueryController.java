package com.geogieoddae.mainservice.report.query.controller;

import com.geogieoddae.mainservice.global.common.dto.ApiResponse;
import com.geogieoddae.mainservice.report.query.dto.ReportQueryDto;
import com.geogieoddae.mainservice.report.query.service.ReportQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/reports")
@RequiredArgsConstructor
public class ReportQueryController {

    private final ReportQueryService reportService;

    // 관리자용 신고 목록 조회 (게시글처럼 조회)
    @GetMapping
    public ResponseEntity<ApiResponse<List<ReportQueryDto>>> listReports() {
        List<ReportQueryDto> result = reportService.listReports();
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 관리자용 신고 상세 조회
    @GetMapping("/{reportCode}")
    public ResponseEntity<ApiResponse<ReportQueryDto>> getReport(@PathVariable Long reportCode) {
        ReportQueryDto result = reportService.getReport(reportCode);
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}