package com.ohgiraffers.geogieoddae.sports.query.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.sports.query.dto.SportQueryDto;
import com.ohgiraffers.geogieoddae.sports.query.dto.TeamQueryDto;
import com.ohgiraffers.geogieoddae.sports.query.service.SportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sports")
@RequiredArgsConstructor
public class SportsController {

    private final SportsService sportsService;

    // 스포츠 종목 목록 조회
    @GetMapping
    public ResponseEntity<ApiResponse<List<SportQueryDto>>> listSports() {
        List<SportQueryDto> result = sportsService.listSports();
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 스포츠 종목 상세 조회
    @GetMapping("/{sportCode}")
    public ResponseEntity<ApiResponse<SportQueryDto>> detailSport(@PathVariable Long sportCode) {
        SportQueryDto result = sportsService.detailSport(sportCode);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 스포츠 팀 목록 조회 (전체)
    @GetMapping("/teams")
    public ResponseEntity<ApiResponse<List<TeamQueryDto>>> listTeams() {
        List<TeamQueryDto> result = sportsService.listTeams();
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 특정 스포츠 종목의 팀 목록 조회
    @GetMapping("/{sportCode}/teams")
    public ResponseEntity<ApiResponse<List<TeamQueryDto>>> listTeamsBySportCode(@PathVariable Long sportCode) {
        List<TeamQueryDto> result = sportsService.listTeamsBySportCode(sportCode);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    // 스포츠 팀 상세 조회
    @GetMapping("/teams/{teamCode}")
    public ResponseEntity<ApiResponse<TeamQueryDto>> detailTeam(@PathVariable Long teamCode) {
        TeamQueryDto result = sportsService.detailTeam(teamCode);
        return ResponseEntity.ok(ApiResponse.success(result));
    }
}