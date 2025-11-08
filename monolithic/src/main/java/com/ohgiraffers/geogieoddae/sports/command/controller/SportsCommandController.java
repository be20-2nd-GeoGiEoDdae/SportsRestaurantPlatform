package com.ohgiraffers.geogieoddae.sports.command.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.sports.command.dto.SportsRequestDto;
import com.ohgiraffers.geogieoddae.sports.command.dto.SportsResponseDto;
import com.ohgiraffers.geogieoddae.sports.command.dto.TeamRequestDto;
import com.ohgiraffers.geogieoddae.sports.command.dto.TeamResponseDto;
import com.ohgiraffers.geogieoddae.sports.command.service.SportsCommandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@Tag(name = "스포츠 관리 api")
@RestController
@RequestMapping("/api/admin/sports")
@RequiredArgsConstructor
public class SportsCommandController {
    private final SportsCommandService sportsCommandService;

    // 스포츠 종목 등록
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createSport(@RequestBody SportsRequestDto sportsRequestDto) {
        sportsCommandService.createSport(sportsRequestDto);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // 스포츠 종목 수정
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{sportCode}")
    public ResponseEntity<ApiResponse<SportsResponseDto>> updateSport(
            @PathVariable Long sportCode,
            @RequestBody SportsRequestDto sportsRequestDto) {
        SportsResponseDto sportsResponseDto = sportsCommandService.updateSport(sportsRequestDto, sportCode);
        return ResponseEntity.ok(ApiResponse.success(sportsResponseDto));
    }

    // 스포츠 종목 삭제
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{sportCode}")
    public ResponseEntity<ApiResponse<Void>> deleteSport(@PathVariable Long sportCode) {
        sportsCommandService.deleteSport(sportCode);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // 스포츠 팀 등록
    @PostMapping("/teams")
    public ResponseEntity<ApiResponse<Void>> createTeam(@RequestBody TeamRequestDto teamRequestDto) {
        sportsCommandService.createTeam(teamRequestDto);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // 스포츠 팀 수정
    @PatchMapping("/teams/{teamCode}")
    public ResponseEntity<ApiResponse<TeamResponseDto>> updateTeam(
            @PathVariable Long teamCode,
            @RequestBody TeamRequestDto teamRequestDto) {
        TeamResponseDto teamResponseDto = sportsCommandService.updateTeam(teamRequestDto, teamCode);
        return ResponseEntity.ok(ApiResponse.success(teamResponseDto));
    }

    // 스포츠 팀 삭제
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/teams/{teamCode}")
    public ResponseEntity<ApiResponse<Void>> deleteTeam(@PathVariable Long teamCode) {
        sportsCommandService.deleteTeam(teamCode);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}