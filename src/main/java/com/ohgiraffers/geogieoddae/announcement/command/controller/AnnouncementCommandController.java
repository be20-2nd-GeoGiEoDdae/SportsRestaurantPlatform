package com.ohgiraffers.geogieoddae.announcement.command.controller;


import com.ohgiraffers.geogieoddae.announcement.command.DTO.AnnouncementRequestDto;
import com.ohgiraffers.geogieoddae.announcement.command.DTO.AnnouncementResponseDto;
import com.ohgiraffers.geogieoddae.announcement.command.service.AnnouncementCommandService;
import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
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
