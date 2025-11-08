package com.geogieoddae.mainservice.announcement.command.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementResponseDto {
    private Long announcementCode;
    private String announcementTitle;
    private String announcementContent;
}

