package com.ohgiraffers.geogieoddae.report.query.mapper;

import com.ohgiraffers.geogieoddae.report.query.dto.ReportQueryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {
    
    // 관리자용 신고 목록 조회
    List<ReportQueryDto> selectReportList();
    
    // 관리자용 신고 상세 조회
    ReportQueryDto selectReportByCode(Long reportCode);
}
