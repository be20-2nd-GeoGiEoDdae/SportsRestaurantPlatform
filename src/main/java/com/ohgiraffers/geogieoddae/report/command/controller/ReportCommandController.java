package com.ohgiraffers.geogieoddae.report.command.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.report.command.dto.ReportRequestDto;
import com.ohgiraffers.geogieoddae.report.command.dto.ReportResponseDto;
import com.ohgiraffers.geogieoddae.report.command.dto.ReportStatusUpdateDto;
import com.ohgiraffers.geogieoddae.report.command.dto.ReportTypeRequestDto;
import com.ohgiraffers.geogieoddae.report.command.dto.ReportTypeResponseDto;
import com.ohgiraffers.geogieoddae.report.command.dto.RestaurantBlacklistRequestDto;
import com.ohgiraffers.geogieoddae.report.command.service.ReportCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportCommandController {

    private final ReportCommandService reportCommandService;

    // 회원 신고 작성
    @PostMapping("/{userCode}")
    public ResponseEntity<ApiResponse<ReportResponseDto>> createReport(
            @PathVariable Long userCode,
            @RequestBody ReportRequestDto reportRequestDto) {
        ReportResponseDto result = reportCommandService.createReport(reportRequestDto, userCode);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 관리자: 신고 승인 또는 미승인 처리
    @PatchMapping("/{reportCode}/status")
    public ResponseEntity<ApiResponse<ReportResponseDto>> updateReportStatus(
            @PathVariable Long reportCode,
            @RequestBody ReportStatusUpdateDto reportStatusUpdateDto) {
        ReportResponseDto result = reportCommandService.updateReportStatus(reportCode, reportStatusUpdateDto);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 관리자: 신고 삭제
    @DeleteMapping("/{reportCode}")
    public ResponseEntity<ApiResponse<Void>> deleteReport(@PathVariable Long reportCode) {
        reportCommandService.deleteReport(reportCode);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

//    // 관리자: 규칙을 어긴 가게를 일정 기간 계정 정지 (블랙리스트 추가)
//    @PostMapping("/blacklist")
//    public ResponseEntity<ApiResponse<Void>> addToBlacklist(@RequestBody RestaurantBlacklistRequestDto restaurantBlacklistRequestDto) {
//        reportCommandService.addToBlacklist(restaurantBlacklistRequestDto);
//        return ResponseEntity.ok(ApiResponse.success(null));
//    }

    // 관리자: 신고 유형 등록
    @PostMapping("/types")
    public ResponseEntity<ApiResponse<ReportTypeResponseDto>> createReportType(@RequestBody ReportTypeRequestDto reportTypeRequestDto) {
        ReportTypeResponseDto result = reportCommandService.createReportType(reportTypeRequestDto);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 관리자: 신고 유형 삭제
    @DeleteMapping("/types/{reportTypeCode}")
    public ResponseEntity<ApiResponse<Void>> deleteReportType(@PathVariable Long reportTypeCode) {
        reportCommandService.deleteReportType(reportTypeCode);
        return ResponseEntity.ok(ApiResponse.success(null));
    }





    /**
     * ✅ 관리자 수동 등록
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> addToBlackList(@RequestBody RestaurantBlacklistRequestDto requestDto) {
        reportCommandService.addToBlacklist(requestDto);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /**
     * ✅ 관리자 수동 삭제
     */
    @DeleteMapping("/{restaurantCode}")
    public ResponseEntity<ApiResponse<Void>> removeFromBlackList(@PathVariable Long restaurantCode) {
        reportCommandService.removeFromBlackList(restaurantCode);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}