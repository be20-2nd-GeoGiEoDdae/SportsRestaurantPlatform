package com.ohgiraffers.geogieoddae.announcement.command.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementCreate {
    private String announcementTitle;
    private String announcementContent;
//    private String announcementAuthor;
//    private Long adminCode;
}
