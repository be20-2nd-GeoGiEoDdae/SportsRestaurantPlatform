package com.ohgiraffers.geogieoddae.announcement.command.service;

import com.ohgiraffers.geogieoddae.admin.command.entity.AdminEntity;
import com.ohgiraffers.geogieoddae.admin.command.repository.AdminRepository;
import com.ohgiraffers.geogieoddae.announcement.command.DTO.AnnouncementCreate;
import com.ohgiraffers.geogieoddae.announcement.command.DTO.AnnouncementUpdateDto;
import com.ohgiraffers.geogieoddae.announcement.command.entity.AnnouncementEntity;
import com.ohgiraffers.geogieoddae.announcement.command.repository.AnnouncementCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnnouncementService {

    private final AnnouncementCommandRepository announcementRepository;

    public Long create(AnnouncementCreate req) {

        AnnouncementEntity entity = AnnouncementEntity.builder()
                .announcementTitle(req.getAnnouncementTitle())
                .announcementContent(req.getAnnouncementContent())
                .build();

        return announcementRepository.save(entity).getAnnouncementCode();
    }

//    public void update(Long code, AnnouncementUpdateDto req) {
//        AnnouncementEntity entity = announcementRepository.findById(code)
//                .orElseThrow(() -> new IllegalArgumentException("공지 없음: " + code));
//
//        entity.setAnnouncementTitle(req.getAnnouncementTitle());
//        entity.setAnnouncementContent(req.getAnnouncementContent());
//        // @Version 사용 시 버전 검증 로직 추가 가능
//    }

//    public void delete(Long code) {
//        if (!announcementRepository.existsById(code)) {
//            throw new IllegalArgumentException("공지 없음: " + code);
//        }
//        announcementRepository.deleteById(code);
//    }
}
