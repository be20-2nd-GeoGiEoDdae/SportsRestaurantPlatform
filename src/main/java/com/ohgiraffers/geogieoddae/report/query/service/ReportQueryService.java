package com.ohgiraffers.geogieoddae.report.query.service;

import com.ohgiraffers.geogieoddae.report.query.dto.ReportQueryDto;
import com.ohgiraffers.geogieoddae.report.query.mapper.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportQueryService {

    private final ReportMapper reportMapper;

    // 관리자용 신고 목록 조회 (게시글처럼 조회)
    public List<ReportQueryDto> listReports() {
        return reportMapper.selectReportList();
    }

    // 관리자용 신고 상세 조회
    public ReportQueryDto getReport(Long reportCode) {
        ReportQueryDto result = reportMapper.selectReportByCode(reportCode);
        if (result == null) {
            throw new IllegalArgumentException("신고가 존재하지 않습니다.: " + reportCode);
        }
        return result;
    }
}