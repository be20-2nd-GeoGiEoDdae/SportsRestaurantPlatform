package com.ohgiraffers.geogieoddae.announcement.query.service;

import com.ohgiraffers.geogieoddae.announcement.query.dto.AnnouncementQueryDto;
import com.ohgiraffers.geogieoddae.announcement.query.mapper.AnnouncementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnnouncementService {

    private final AnnouncementMapper announcementMapper;

    //공지 목록 조회
    public List<AnnouncementQueryDto> list() {
        return announcementMapper.selectAnnouncementList();
    }

    //공지 상세 조회
    public AnnouncementQueryDto detail(Long announcementCode) {
        AnnouncementQueryDto result = announcementMapper.selectAnnouncementByCode(announcementCode);
        if (result == null) {
            throw new IllegalArgumentException("공지가 존재하지 않습니다.: " + announcementCode);
        }
        return result;
    }
}
