package com.ohgiraffers.geogieoddae.report.query.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.report.query.dto.ReportQueryDto;
import com.ohgiraffers.geogieoddae.report.query.service.ReportQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "관리자 신고 조회 api")
@RestController
@RequestMapping("/api/admin/reports")
@RequiredArgsConstructor
public class ReportQueryController {

    private final ReportQueryService reportService;

    // 관리자용 신고 목록 조회 (게시글처럼 조회)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<ReportQueryDto>>> listReports() {
        List<ReportQueryDto> result = reportService.listReports();
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 관리자용 신고 상세 조회
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{reportCode}")
    public ResponseEntity<ApiResponse<ReportQueryDto>> getReport(@PathVariable Long reportCode) {
        ReportQueryDto result = reportService.getReport(reportCode);
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}