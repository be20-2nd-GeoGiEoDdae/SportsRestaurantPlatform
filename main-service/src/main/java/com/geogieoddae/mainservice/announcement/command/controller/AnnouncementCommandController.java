package com.geogieoddae.mainservice.announcement.command.controller;

import com.geogieoddae.mainservice.announcement.command.DTO.AnnouncementRequestDto;
import com.geogieoddae.mainservice.announcement.command.DTO.AnnouncementResponseDto;
import com.geogieoddae.mainservice.announcement.command.service.AnnouncementCommandService;
import com.geogieoddae.mainservice.global.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/announcements")
@RequiredArgsConstructor
public class AnnouncementCommandController {
    private final AnnouncementCommandService announcementCommandService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody AnnouncementRequestDto dto) {
        announcementCommandService.create(dto);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PatchMapping("/{code}")
    public ResponseEntity<ApiResponse<AnnouncementResponseDto>> update(@PathVariable Long code, @RequestBody AnnouncementRequestDto dto){
        AnnouncementResponseDto response = announcementCommandService.update(dto, code);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long code){
        announcementCommandService.delete(code);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
