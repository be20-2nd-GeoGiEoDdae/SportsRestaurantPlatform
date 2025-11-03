package com.ohgiraffers.geogieoddae.report.command.service;

import com.ohgiraffers.geogieoddae.report.command.dto.ReportRequestDto;
import com.ohgiraffers.geogieoddae.report.command.dto.ReportResponseDto;
import com.ohgiraffers.geogieoddae.report.command.dto.ReportStatusUpdateDto;
import com.ohgiraffers.geogieoddae.report.command.dto.ReportTypeRequestDto;
import com.ohgiraffers.geogieoddae.report.command.dto.ReportTypeResponseDto;
import com.ohgiraffers.geogieoddae.report.command.dto.RestaurantBlacklistRequestDto;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;
import com.ohgiraffers.geogieoddae.report.command.entity.ReportEntity;
import com.ohgiraffers.geogieoddae.report.command.entity.ReportStatus;
import com.ohgiraffers.geogieoddae.report.command.entity.ReportTypeEntity;
import com.ohgiraffers.geogieoddae.report.command.entity.blacklist.RestaurantBlacklistEntity;
import com.ohgiraffers.geogieoddae.report.command.repository.ReportRepository;
import com.ohgiraffers.geogieoddae.report.command.repository.ReportTypeRepository;
import com.ohgiraffers.geogieoddae.report.command.repository.RestaurantBlacklistRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReportTypeRepository reportTypeRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final RestaurantBlacklistRepository restaurantBlacklistRepository;

    // 회원 신고 작성
    public ReportResponseDto createReport(ReportRequestDto reportRequestDto, Long userCode) {
        // RestaurantEntity 조회
        RestaurantEntity restaurant = restaurantRepository.findById(reportRequestDto.getRestaurantCode())
                .orElseThrow(() -> new IllegalArgumentException("가게가 존재하지 않습니다.: " + reportRequestDto.getRestaurantCode()));

        // ReportTypeEntity 조회
        ReportTypeEntity reportType = reportTypeRepository.findById(reportRequestDto.getReportTypeCode())
                .orElseThrow(() -> new IllegalArgumentException("신고 타입이 존재하지 않습니다.: " + reportRequestDto.getReportTypeCode()));

        // UserEntity 조회
        UserEntity user = userRepository.findById(userCode)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다.: " + userCode));

        // reportCode 생성 (가장 큰 값 + 1)
        Long nextReportCode = reportRepository.findAll().stream()
                .mapToLong(ReportEntity::getReportCode)
                .max()
                .orElse(0L) + 1;

        // 제목과 내용을 합쳐서 reportContents에 저장 (제목\n내용 형식)
        String combinedContents = reportRequestDto.getReportTitle() + "\n" + reportRequestDto.getReportContents();

        // ReportEntity 생성
        ReportEntity reportEntity = ReportEntity.builder()
                .reportCode(nextReportCode)
                .reportContents(combinedContents)
                .reportStatus(ReportStatus.PENDING)
                .restaurant(restaurant)
                .reportType(reportType)
                .member(user)
                .build();

        ReportEntity savedEntity = reportRepository.save(reportEntity);

        return ReportResponseDto.builder()
                .reportCode(savedEntity.getReportCode())
                .reportTitle(reportRequestDto.getReportTitle())
                .reportContents(reportRequestDto.getReportContents())
                .reportStatus(savedEntity.getReportStatus())
                .restaurantCode(savedEntity.getRestaurant().getRestaurantCode())
                .restaurantName(savedEntity.getRestaurant().getRestaurantName())
                .reportTypeCode(savedEntity.getReportType().getReportTypeCode())
                .reportTypeType(savedEntity.getReportType().getReportTypeType())
                .userCode(savedEntity.getMember().getUserCode())
                .build();
    }

    // 관리자: 신고 승인 또는 미승인 처리
    public ReportResponseDto updateReportStatus(Long reportCode, ReportStatusUpdateDto reportStatusUpdateDto) {
        ReportEntity reportEntity = reportRepository.findById(reportCode)
                .orElseThrow(() -> new IllegalArgumentException("신고가 존재하지 않습니다.: " + reportCode));

        reportEntity.setReportStatus(reportStatusUpdateDto.getReportStatus());

        // reportContents에서 제목과 내용 분리
        String[] parts = reportEntity.getReportContents().split("\n", 2);
        String title = parts.length > 0 ? parts[0] : "";
        String contents = parts.length > 1 ? parts[1] : "";

        return ReportResponseDto.builder()
                .reportCode(reportEntity.getReportCode())
                .reportTitle(title)
                .reportContents(contents)
                .reportStatus(reportEntity.getReportStatus())
                .restaurantCode(reportEntity.getRestaurant().getRestaurantCode())
                .restaurantName(reportEntity.getRestaurant().getRestaurantName())
                .reportTypeCode(reportEntity.getReportType().getReportTypeCode())
                .reportTypeType(reportEntity.getReportType().getReportTypeType())
                .userCode(reportEntity.getMember().getUserCode())
                .build();
    }

    // 관리자: 신고 삭제
    public void deleteReport(Long reportCode) {
        ReportEntity reportEntity = reportRepository.findById(reportCode)
                .orElseThrow(() -> new IllegalArgumentException("신고가 존재하지 않습니다.: " + reportCode));
        
        reportRepository.delete(reportEntity);
    }

    // 관리자: 규칙을 어긴 가게를 일정 기간 계정 정지 (블랙리스트 추가)
    public void addToBlacklist(RestaurantBlacklistRequestDto request) {
        // ReportEntity 조회
        ReportEntity reportEntity = reportRepository.findById(request.getReportCode())
                .orElseThrow(() -> new IllegalArgumentException("신고가 존재하지 않습니다.: " + request.getReportCode()));

        // RestaurantEntity 조회
        RestaurantEntity restaurant = restaurantRepository.findById(request.getRestaurantCode())
                .orElseThrow(() -> new IllegalArgumentException("가게가 존재하지 않습니다.: " + request.getRestaurantCode()));

        // 이미 블랙리스트에 있는지 확인
        if (restaurantBlacklistRepository.existsById(request.getReportCode())) {
            throw new IllegalArgumentException("이미 블랙리스트에 등록된 신고입니다.");
        }

        // RestaurantBlacklistEntity 생성
        RestaurantBlacklistEntity blacklistEntity = RestaurantBlacklistEntity.builder()
                .reportCode(request.getReportCode())
                .restaurantBlacklistName(restaurant.getRestaurantName())
                .restaurant(restaurant)
                .report(reportEntity)
                .build();

        restaurantBlacklistRepository.save(blacklistEntity);
    }

    // 관리자: 신고 유형 등록
    public ReportTypeResponseDto createReportType(ReportTypeRequestDto reportTypeRequestDto) {
        // 이미 존재하는 신고 유형인지 확인
        if (reportTypeRepository.existsById(reportTypeRequestDto.getReportTypeCode())) {
            throw new IllegalArgumentException("이미 존재하는 신고 유형입니다.: " + reportTypeRequestDto.getReportTypeCode());
        }

        ReportTypeEntity reportTypeEntity = ReportTypeEntity.builder()
                .reportTypeCode(reportTypeRequestDto.getReportTypeCode())
                .reportTypeType(reportTypeRequestDto.getReportTypeType())
                .build();

        ReportTypeEntity savedEntity = reportTypeRepository.save(reportTypeEntity);

        return ReportTypeResponseDto.builder()
                .reportTypeCode(savedEntity.getReportTypeCode())
                .reportTypeType(savedEntity.getReportTypeType())
                .build();
    }

    // 관리자: 신고 유형 삭제
    public void deleteReportType(String reportTypeCode) {
        ReportTypeEntity reportType = reportTypeRepository.findById(reportTypeCode)
                .orElseThrow(() -> new IllegalArgumentException("신고 유형이 존재하지 않습니다.: " + reportTypeCode));

        // 해당 신고 유형을 사용하는 신고가 있는지 확인
        if (reportType.getReports() != null && !reportType.getReports().isEmpty()) {
            throw new IllegalArgumentException("해당 신고 유형을 사용하는 신고가 있어 삭제할 수 없습니다.: " + reportTypeCode);
        }

        reportTypeRepository.delete(reportType);
    }
}