package com.geogieoddae.mainservice.announcement.query.controller;

import com.geogieoddae.mainservice.announcement.query.dto.AnnouncementQueryDto;
import com.geogieoddae.mainservice.announcement.query.service.AnnouncementQueryService;
import com.geogieoddae.mainservice.global.common.dto.ApiResponse;
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
