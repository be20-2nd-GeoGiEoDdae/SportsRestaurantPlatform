package com.ohgiraffers.geogieoddae.viewing.command.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.viewing.command.dto.ViewingDto;
import com.ohgiraffers.geogieoddae.viewing.command.dto.ViewingUserDto;
import com.ohgiraffers.geogieoddae.viewing.command.service.ViewingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "관람 관리 api")
@RestController
@RequestMapping("/api/viewings")
@RequiredArgsConstructor
public class ViewingController {

    private final ViewingService viewingService;

    /* ====================== 사업자 기능 ====================== */

//    @PreAuthorize("hasRole('ENTREPRENEUR')")
    @PostMapping
    public ResponseEntity<ApiResponse<?>> createViewing(@RequestBody ViewingDto dto) {
        viewingService.createViewing(dto);
        return ResponseEntity.ok(ApiResponse.success(dto));
    }

    /**
     * 관람 수정 (사업자)
     */
    @PreAuthorize("hasRole('ENTREPRENEUR')")
    @PutMapping("/{viewingCode}")
    public ResponseEntity<ApiResponse<?>> updateViewing(
            @PathVariable Long viewingCode,
            @RequestBody ViewingDto dto
    ) {
        dto.setViewingCode(viewingCode);
        viewingService.updateViewing(dto);
        return ResponseEntity.ok(ApiResponse.success(dto));
    }

    /**
     * 관람 삭제 (사업자)
     */
    @PreAuthorize("hasRole('ENTREPRENEUR')")
    @DeleteMapping("/{viewingCode}")
    public ResponseEntity<ApiResponse<?>> deleteViewing(@PathVariable Long viewingCode) {
        viewingService.deleteViewing(viewingCode);
        return ResponseEntity.ok(ApiResponse.success(null));
    }


    /* ====================== 이용자 기능 ====================== */

    /**
     * 관람 참가 (이용자)
     */
    @PostMapping("/{viewingCode}/apply")
    public ResponseEntity<ApiResponse<?>> applyViewing(
            @PathVariable Long viewingCode,
            @RequestBody ViewingUserDto dto
    ) {
        dto.setViewingCode(viewingCode);
        viewingService.applyViewing(dto);
        return ResponseEntity.ok(ApiResponse.success(dto));
    }

    /**
     * 관람 참가 정보 수정 (이용자)
     */
    @PutMapping("/user/{viewingUserCode}")
    public ResponseEntity<ApiResponse<?>> modifyViewing(
            @PathVariable Long viewingUserCode,
            @RequestBody ViewingUserDto dto
    ) {
        dto.setViewingUserCode(viewingUserCode);
        viewingService.modifyViewing(dto);
        return ResponseEntity.ok(ApiResponse.success(dto));
    }

    /**
     * 관람 참가 취소 (이용자)
     */
    @DeleteMapping("/user/{viewingUserCode}")
    public ResponseEntity<ApiResponse<?>> cancelViewing(@PathVariable Long viewingUserCode) {
        viewingService.cancelViewing(viewingUserCode);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
