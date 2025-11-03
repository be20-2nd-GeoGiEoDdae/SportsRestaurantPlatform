package com.ohgiraffers.geogieoddae.announcement.command.controller;


import com.ohgiraffers.geogieoddae.announcement.command.DTO.AnnouncementRequestDto;
import com.ohgiraffers.geogieoddae.announcement.command.DTO.AnnouncementResponseDto;
import com.ohgiraffers.geogieoddae.announcement.command.service.AnnouncementService;
import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/announcements")
@RequiredArgsConstructor
public class AnnouncementCommandController {
    private final AnnouncementService announcementService;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(@RequestBody AnnouncementRequestDto dto) {
        announcementService.create(dto);
        return ResponseEntity.ok(ApiResponse.success(null));
//        return ResponseEntity.ok(ApiResponse.failure(400, " 등록 실패"));
    }

    @PatchMapping("/{code}")
    public ResponseEntity<ApiResponse<AnnouncementResponseDto>> update(@PathVariable Long code, @RequestBody AnnouncementRequestDto dto){
        AnnouncementResponseDto response = announcementService.update(dto, code);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long code){
        announcementService.delete(code);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
