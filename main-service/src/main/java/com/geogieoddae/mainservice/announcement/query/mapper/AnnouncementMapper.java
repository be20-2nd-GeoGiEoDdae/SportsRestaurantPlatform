package com.geogieoddae.mainservice.announcement.query.mapper;

import com.geogieoddae.mainservice.announcement.query.dto.AnnouncementQueryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnouncementMapper {
    
    //공지 목록 조회
    List<AnnouncementQueryDto> selectAnnouncementList();
    
    //공지 상세 조회
    AnnouncementQueryDto selectAnnouncementByCode(Long announcementCode);
}
