package com.ohgiraffers.geogieoddae.announcement.command.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementUpdateDto {
    private String AnnouncementContent;
    private String AnnouncementTitle;
}
