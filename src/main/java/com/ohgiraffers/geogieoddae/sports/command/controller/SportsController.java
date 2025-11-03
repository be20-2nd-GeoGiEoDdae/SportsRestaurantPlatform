package com.ohgiraffers.geogieoddae.sports.command.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.sports.command.dto.SportRequestDto;
import com.ohgiraffers.geogieoddae.sports.command.dto.SportResponseDto;
import com.ohgiraffers.geogieoddae.sports.command.dto.TeamRequestDto;
import com.ohgiraffers.geogieoddae.sports.command.dto.TeamResponseDto;
import com.ohgiraffers.geogieoddae.sports.command.service.SportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/sports")
@RequiredArgsConstructor
public class SportsController {
    private final SportsService sportsService;

    // 스포츠 종목 등록
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createSport(@RequestBody SportRequestDto sportRequestDto) {
        sportsService.createSport(sportRequestDto);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // 스포츠 종목 수정
    @PatchMapping("/{sportCode}")
    public ResponseEntity<ApiResponse<SportResponseDto>> updateSport(
            @PathVariable Long sportCode,
            @RequestBody SportRequestDto sportRequestDto) {
        SportResponseDto sportResponseDto = sportsService.updateSport(sportRequestDto, sportCode);
        return ResponseEntity.ok(ApiResponse.success(sportResponseDto));
    }

    // 스포츠 종목 삭제
    @DeleteMapping("/{sportCode}")
    public ResponseEntity<ApiResponse<Void>> deleteSport(@PathVariable Long sportCode) {
        sportsService.deleteSport(sportCode);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // 스포츠 팀 등록
    @PostMapping("/teams")
    public ResponseEntity<ApiResponse<Void>> createTeam(@RequestBody TeamRequestDto teamRequestDto) {
        sportsService.createTeam(teamRequestDto);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // 스포츠 팀 수정
    @PatchMapping("/teams/{teamCode}")
    public ResponseEntity<ApiResponse<TeamResponseDto>> updateTeam(
            @PathVariable Long teamCode,
            @RequestBody TeamRequestDto teamRequestDto) {
        TeamResponseDto teamResponseDto = sportsService.updateTeam(teamRequestDto, teamCode);
        return ResponseEntity.ok(ApiResponse.success(teamResponseDto));
    }

    // 스포츠 팀 삭제
    @DeleteMapping("/teams/{teamCode}")
    public ResponseEntity<ApiResponse<Void>> deleteTeam(@PathVariable Long teamCode) {
        sportsService.deleteTeam(teamCode);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}