package com.ohgiraffers.geogieoddae.announcement.query.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementQueryDto {
    private String announcementTitle;
    private String announcementContent;
    private Long adminCode;
}
