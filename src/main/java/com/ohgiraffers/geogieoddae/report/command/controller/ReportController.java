package com.ohgiraffers.geogieoddae.report.command.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.report.command.dto.ReportRequestDto;
import com.ohgiraffers.geogieoddae.report.command.dto.ReportResponseDto;
import com.ohgiraffers.geogieoddae.report.command.dto.ReportStatusUpdateDto;
import com.ohgiraffers.geogieoddae.report.command.dto.ReportTypeRequestDto;
import com.ohgiraffers.geogieoddae.report.command.dto.ReportTypeResponseDto;
import com.ohgiraffers.geogieoddae.report.command.dto.RestaurantBlacklistRequestDto;
import com.ohgiraffers.geogieoddae.report.command.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    // 회원 신고 작성
    @PostMapping
    public ResponseEntity<ApiResponse<ReportResponseDto>> createReport(
            @RequestBody ReportRequestDto request,
            @RequestHeader("User-Code") Long userCode) {
        ReportResponseDto result = reportService.createReport(request, userCode);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 관리자: 신고 승인 또는 미승인 처리
    @PatchMapping("/{reportCode}/status")
    public ResponseEntity<ApiResponse<ReportResponseDto>> updateReportStatus(
            @PathVariable Long reportCode,
            @RequestBody ReportStatusUpdateDto request) {
        ReportResponseDto result = reportService.updateReportStatus(reportCode, request);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 관리자: 신고 삭제
    @DeleteMapping("/{reportCode}")
    public ResponseEntity<ApiResponse<Void>> deleteReport(@PathVariable Long reportCode) {
        reportService.deleteReport(reportCode);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // 관리자: 규칙을 어긴 가게를 일정 기간 계정 정지 (블랙리스트 추가)
    @PostMapping("/blacklist")
    public ResponseEntity<ApiResponse<Void>> addToBlacklist(@RequestBody RestaurantBlacklistRequestDto request) {
        reportService.addToBlacklist(request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // 관리자: 신고 유형 등록
    @PostMapping("/types")
    public ResponseEntity<ApiResponse<ReportTypeResponseDto>> createReportType(@RequestBody ReportTypeRequestDto request) {
        ReportTypeResponseDto result = reportService.createReportType(request);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 관리자: 신고 유형 삭제
    @DeleteMapping("/types/{reportTypeCode}")
    public ResponseEntity<ApiResponse<Void>> deleteReportType(@PathVariable String reportTypeCode) {
        reportService.deleteReportType(reportTypeCode);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}