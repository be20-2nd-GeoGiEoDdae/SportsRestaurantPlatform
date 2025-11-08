package com.geogieoddae.mainservice.sports.command.controller;

import com.geogieoddae.mainservice.global.common.dto.ApiResponse;
import com.geogieoddae.mainservice.sports.command.dto.SportsRequestDto;
import com.geogieoddae.mainservice.sports.command.dto.SportsResponseDto;
import com.geogieoddae.mainservice.sports.command.dto.TeamRequestDto;
import com.geogieoddae.mainservice.sports.command.dto.TeamResponseDto;
import com.geogieoddae.mainservice.sports.command.service.SportsCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/sports")
@RequiredArgsConstructor
public class SportsCommandController {
    private final SportsCommandService sportsCommandService;

    // 스포츠 종목 등록
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createSport(@RequestBody SportsRequestDto sportsRequestDto) {
        sportsCommandService.createSport(sportsRequestDto);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // 스포츠 종목 수정
        @PatchMapping("/{sportCode}")
    public ResponseEntity<ApiResponse<SportsResponseDto>> updateSport(
            @PathVariable Long sportCode,
            @RequestBody SportsRequestDto sportsRequestDto) {
        SportsResponseDto sportsResponseDto = sportsCommandService.updateSport(sportsRequestDto, sportCode);
        return ResponseEntity.ok(ApiResponse.success(sportsResponseDto));
    }

    // 스포츠 종목 삭제
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
    @DeleteMapping("/teams/{teamCode}")
    public ResponseEntity<ApiResponse<Void>> deleteTeam(@PathVariable Long teamCode) {
        sportsCommandService.deleteTeam(teamCode);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}