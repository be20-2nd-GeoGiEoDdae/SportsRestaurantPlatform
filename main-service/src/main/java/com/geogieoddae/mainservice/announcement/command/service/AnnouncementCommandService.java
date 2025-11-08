package com.geogieoddae.mainservice.announcement.command.service;

import com.geogieoddae.mainservice.announcement.command.DTO.AnnouncementRequestDto;
import com.geogieoddae.mainservice.announcement.command.DTO.AnnouncementResponseDto;
import com.geogieoddae.mainservice.announcement.command.entity.AnnouncementEntity;
import com.geogieoddae.mainservice.announcement.command.repository.AnnouncementCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnnouncementCommandService {

    private final AnnouncementCommandRepository announcementRepository;

    //공지 생성
    public void create(AnnouncementRequestDto announcementRequestDto) {

        AnnouncementEntity announcementEntity = AnnouncementEntity.builder()
                .announcementTitle(announcementRequestDto.getAnnouncementTitle())
                .announcementContent(announcementRequestDto.getAnnouncementContent())
                .build();

       announcementRepository.save(announcementEntity);
    }

    //공지 수정
    public AnnouncementResponseDto update(AnnouncementRequestDto announcementRequestDto, Long announcementCode){
        AnnouncementEntity entity = announcementRepository.findById(announcementCode)
                 .orElseThrow(() -> new IllegalArgumentException("공지가 존재하지 않습니다.: " + announcementCode));

        entity.setAnnouncementTitle(announcementRequestDto.getAnnouncementTitle());
        entity.setAnnouncementContent(announcementRequestDto.getAnnouncementContent());

        

        return AnnouncementResponseDto.builder()
                .announcementCode(entity.getAnnouncementCode())
                .announcementTitle(entity.getAnnouncementTitle())
                .announcementContent(entity.getAnnouncementContent())
                .build();
    }

    //공지 삭제
        public void delete(Long announcementCode){
            if (!announcementRepository.existsById(announcementCode)) {
                throw new IllegalArgumentException("공지가 존재하지 않습니다.: " + announcementCode);
            }
            announcementRepository.deleteById(announcementCode);
    }

}
