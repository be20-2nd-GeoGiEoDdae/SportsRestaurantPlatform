package com.ohgiraffers.geogieoddae.announcement.command.controller;


import com.ohgiraffers.geogieoddae.announcement.command.DTO.AnnouncementRequestDto;
import com.ohgiraffers.geogieoddae.announcement.command.DTO.AnnouncementResponseDto;
import com.ohgiraffers.geogieoddae.announcement.command.service.AnnouncementCommandService;
import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "관리자 공지사항 API")
@RestController
@RequestMapping("/api/admin/announcements")
@RequiredArgsConstructor
public class AnnouncementCommandController {
    private final AnnouncementCommandService announcementCommandService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody AnnouncementRequestDto dto) {
        announcementCommandService.create(dto);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{code}")
    public ResponseEntity<ApiResponse<AnnouncementResponseDto>> update(@PathVariable Long code, @RequestBody AnnouncementRequestDto dto){
        AnnouncementResponseDto response = announcementCommandService.update(dto, code);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{code}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long code){
        announcementCommandService.delete(code);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
