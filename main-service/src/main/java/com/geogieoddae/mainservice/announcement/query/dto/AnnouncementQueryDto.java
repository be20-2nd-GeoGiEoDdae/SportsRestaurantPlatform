package com.geogieoddae.mainservice.announcement.query.dto;

import lombok.*;

/**
 * 공지사항 조회용 DTO
 * 작성자 유무 판별 필요 -> 현재 관리자 페이지에서 관리자만 작성 가능하도록 설정
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnouncementQueryDto {
    private Long announcementCode;
    private String announcementTitle;
    private String announcementContent;
}