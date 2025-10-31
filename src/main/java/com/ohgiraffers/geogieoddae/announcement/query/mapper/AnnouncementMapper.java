//package com.ohgiraffers.geogieoddae.announcement.query.mapper;
//
//import com.ohgiraffers.geogieoddae.announcement.command.entity.AnnouncementEntity;
//import com.ohgiraffers.geogieoddae.announcement.query.dto.AnnouncementQueryDto;
//
//public class AnnouncementMapper {
//    public static AnnouncementQueryDto toDto(AnnouncementEntity e) {
//        return new AnnouncementQueryDto(
//                e.getAnnouncementCode(),
//                e.getAnnouncementTitle(),
//                e.getAnnouncementContent(),
//                e.getAnnouncementAuthor(),
//                e.getAdmin() != null ? e.getAdmin().getAdminCode() : null,
//                e.getCreatedAt(),
//                e.getUpdatedAt()
//        );
//    }
//}