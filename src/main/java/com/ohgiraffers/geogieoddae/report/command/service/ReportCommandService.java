package com.ohgiraffers.geogieoddae.report.command.service;

import com.ohgiraffers.geogieoddae.notification.command.event.NotificationCreatedEvent;
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
import com.ohgiraffers.geogieoddae.restaurant.command.repository.restaurant.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportCommandService {

    private final ReportRepository reportRepository;
    private final ReportTypeRepository reportTypeRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final RestaurantBlacklistRepository restaurantBlacklistRepository;
    private final ApplicationEventPublisher publisher;

    // 회원 신고 작성
    public ReportResponseDto createReport(ReportRequestDto reportRequestDto, Long userCode) {
        // RestaurantEntity 조회
        RestaurantEntity restaurant = restaurantRepository.findById(reportRequestDto.getRestaurantCode())
                .orElseThrow(() -> new IllegalArgumentException("가게가 존재하지 않습니다."));

        // ReportTypeEntity 조회
        ReportTypeEntity reportType = reportTypeRepository.findById(reportRequestDto.getReportTypeCode())
                .orElseThrow(() -> new IllegalArgumentException("신고 타입이 존재하지 않습니다."));

        // UserEntity 조회
        UserEntity user = userRepository.findById(userCode)
                .orElseThrow(() -> new IllegalArgumentException("회원정보가 존재하지 않습니다."));


        // ReportEntity 생성
        ReportEntity entity = ReportEntity.builder()
                .reportContents(reportRequestDto.getReportContents())
                .reportStatus(ReportStatus.PENDING)   // 기본값: 보류
                .restaurant(restaurant)
                .reportType(reportType)
                .member(user)
                .build();

        ReportEntity saved = reportRepository.save(entity);
        return ReportResponseDto.builder()
                .reportCode(saved.getReportCode())
                .reportContents(saved.getReportContents())
                .reportStatus(saved.getReportStatus())
                .restaurantCode(saved.getRestaurant().getRestaurantCode())
                .userCode(saved.getMember().getUserCode())
                .build();
    }

    // 관리자: 신고 승인 또는 미승인 처리
    public ReportResponseDto updateReportStatus(Long reportCode, ReportStatusUpdateDto reportStatusUpdateDto) {
        ReportEntity reportEntity = reportRepository.findById(reportCode)
                .orElseThrow(() -> new IllegalArgumentException("신고가 존재하지 않습니다.: " + reportCode));

        reportEntity.setReportStatus(reportStatusUpdateDto.getReportStatus());

        Long notificationType=(long)6;
        publisher.publishEvent(new NotificationCreatedEvent(reportEntity.getRestaurant().getEntrepreneur().getMember().getUserCode(),notificationType));


        if (!reportEntity.getReportStatus().canChangeTo(reportStatusUpdateDto.getReportStatus())) {
            throw new IllegalStateException("유효하지 않은 상태 변경입니다.");
        }


////        상태 값만 따로 넣을 필요가 있음
//        return ReportResponseDto.builder()
//                .reportCode(reportEntity.getReportCode())
//                .reportStatus(reportEntity.getReportStatus())
//                .restaurantCode(reportEntity.getRestaurant().getRestaurantCode())
//                .reportTypeCode(reportEntity.getReportType().getReportTypeCode())
//                .userCode(reportEntity.getMember().getUserCode())
//                .build();
        return null;
    }

    // 관리자: 신고 삭제
    public void deleteReport(Long reportCode) {
        ReportEntity reportEntity = reportRepository.findById(reportCode)
                .orElseThrow(() -> new IllegalArgumentException("신고가 존재하지 않습니다.: " + reportCode));

        reportRepository.delete(reportEntity);
    }



    // 관리자: 신고 유형 등록
    public ReportTypeResponseDto createReportType(ReportTypeRequestDto reportTypeRequestDto) {
        // 이미 존재하는 신고 유형인지 확인

        ReportTypeEntity reportTypeEntity = ReportTypeEntity.builder()
                .reportTypeType(reportTypeRequestDto.getReportTypeType())
                .build();

        ReportTypeEntity savedEntity = reportTypeRepository.save(reportTypeEntity);

        return ReportTypeResponseDto.builder()
                .reportTypeType(savedEntity.getReportTypeType())
                .build();
    }

    // 관리자: 신고 유형 삭제
    public void deleteReportType(Long reportTypeCode) {
        ReportTypeEntity reportType = reportTypeRepository.findById(reportTypeCode)
                .orElseThrow(() -> new IllegalArgumentException("신고 유형이 존재하지 않습니다.: " + reportTypeCode));

        // 해당 신고 유형을 사용하는 신고가 있는지 확인
        if (reportType.getReports() != null && !reportType.getReports().isEmpty()) {
            throw new IllegalArgumentException("해당 신고 유형을 사용하는 신고가 있어 삭제할 수 없습니다.: " + reportTypeCode);
        }

        reportTypeRepository.delete(reportType);
    }


//    /**
//     * ✅ 관리자 수동 추가
//     */
    @Transactional
    public void addToBlacklist(RestaurantBlacklistRequestDto restaurantBlacklistRequestDto) {
        if (restaurantBlacklistRepository.existsById(restaurantBlacklistRequestDto.getReportCode())) {
            throw new IllegalStateException("이미 블랙리스트에 등록된 가게입니다.");
        }

        RestaurantBlacklistEntity restaurantBlacklistEntity = RestaurantBlacklistEntity.builder()
                .reportCode(restaurantBlacklistRequestDto.getReportCode())
                .restaurantBlacklistName("관리자 수동 등록")
                .restaurant( // restaurant_code 직접 연결
                        reportRepository.findById(restaurantBlacklistRequestDto.getReportCode())
                                .orElseThrow(() -> new IllegalArgumentException("신고가 존재하지 않습니다."))
                                .getRestaurant()
                )
                .build();

        Long notificationType=(long)7;
        //Long restaurant =restaurantRepository.findById(restaurantBlacklistRequestDto.getRestaurantCode()).orElseThrow().getEntrepreneur().getMember().getUserCode();

        publisher.publishEvent(new NotificationCreatedEvent(restaurantBlacklistEntity
            .getRestaurant()
            .getEntrepreneur()
            .getMember()
            .getUserCode()
            ,notificationType));


        restaurantBlacklistRepository.save(restaurantBlacklistEntity);
    }

////    /**
////     * ✅ 자동 등록 (신고 5회 이상 시)
////     */
//    @Transactional
//    public void checkAndAddBlackList(Long restaurantCode) {
//        int count = reportRepository.countByRestaurantRestaurantCode(restaurantCode);
//
//        if (count >= 5 && !restaurantBlacklistRepository.existsByRestaurant_RestaurantCode(restaurantCode)) {
//            RestaurantBlacklistEntity restaurantBlacklistEntity = RestaurantBlacklistEntity.builder()
//                    .restaurantBlacklistName("신고 누적 5회 자동 등록")
//                    .restaurant(
//                            reportRepository.findFirstByrestaurentEntity_RestaurantCode(restaurantCode)
//                                    .getRestaurant()
//                    )
//                    .build();
//
//            restaurantBlacklistRepository.save(restaurantBlacklistEntity);
//        }
//    }

    /**
     * ✅ 관리자 수동 삭제
     */
    @Transactional
    public void removeFromBlackList(Long restaurantCode) {
        RestaurantBlacklistEntity restaurantBlacklistEntity=
                restaurantBlacklistRepository.findByRestaurant_RestaurantCode(restaurantCode)
                        .orElseThrow(() -> new IllegalArgumentException("해당 가게는 블랙리스트에 없습니다."));
        restaurantBlacklistRepository.delete(restaurantBlacklistEntity);
    }








}