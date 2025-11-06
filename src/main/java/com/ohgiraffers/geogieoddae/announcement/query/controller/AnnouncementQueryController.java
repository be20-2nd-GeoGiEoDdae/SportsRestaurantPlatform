package com.ohgiraffers.geogieoddae.announcement.query.controller;

import com.ohgiraffers.geogieoddae.announcement.query.dto.AnnouncementQueryDto;
import com.ohgiraffers.geogieoddae.announcement.query.service.AnnouncementQueryService;
import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcements")
@RequiredArgsConstructor
public class AnnouncementQueryController {

    private final AnnouncementQueryService announcementQueryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AnnouncementQueryDto>>> list() {
        List<AnnouncementQueryDto> result = announcementQueryService.list();
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping("/{announcementCode}")
    public ResponseEntity<ApiResponse<AnnouncementQueryDto>> detail(@PathVariable Long announcementCode) {
        AnnouncementQueryDto result = announcementQueryService.detail(announcementCode);
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
