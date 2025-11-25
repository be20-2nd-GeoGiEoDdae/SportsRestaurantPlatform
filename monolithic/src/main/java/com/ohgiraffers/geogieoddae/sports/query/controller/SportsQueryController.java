package com.ohgiraffers.geogieoddae.sports.query.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.sports.command.entity.SportsEntity;
import com.ohgiraffers.geogieoddae.sports.command.repository.SportsRepository;
import com.ohgiraffers.geogieoddae.sports.query.dto.SportsQueryDto;
import com.ohgiraffers.geogieoddae.sports.query.dto.TeamQueryDto;
import com.ohgiraffers.geogieoddae.sports.query.service.SportsQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "스포츠 조회 api")
@RestController
@RequestMapping("/api/sports")
@RequiredArgsConstructor
public class SportsQueryController {

    private final SportsQueryService sportsQueryService;
    private final SportsRepository sportsRepository;
    // 스포츠 종목 목록 조회
    @GetMapping
    public ResponseEntity<ApiResponse<List<SportsQueryDto>>> listSports() {
        List<SportsQueryDto> result = sportsQueryService.listSports();
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping("/{sportId}")
    public ResponseEntity<ApiResponse<?>> getSport(@PathVariable Long sportId) {
        return ResponseEntity.ok(ApiResponse.success(
                sportsQueryService.getSportDto(sportId)
        ));
    }
//    // 스포츠 종목 상세 조회
//    @GetMapping("/{sportCode}")
//    public ResponseEntity<ApiResponse<SportsQueryDto>> detailSport(@PathVariable Long sportCode) {
//        SportsQueryDto result = sportsQueryService.detailSports(sportCode);
//        return ResponseEntity.ok(ApiResponse.success(result));
//    }

    // 스포츠 팀 목록 조회 (전체)
    @GetMapping("/teams")
    public ResponseEntity<ApiResponse<List<TeamQueryDto>>> listTeams() {
        List<TeamQueryDto> result = sportsQueryService.listTeams();
        return ResponseEntity.ok(ApiResponse.success(result));
    }

//    // 특정 스포츠 종목의 팀 목록 조회
//    @GetMapping("/{sportCode}/teams")
//    public ResponseEntity<ApiResponse<List<TeamQueryDto>>> listTeamsBySport(@PathVariable Long sportCode) {
//        List<TeamQueryDto> result = sportsQueryService.listTeamsBySport(sportCode);
//        return ResponseEntity.ok(ApiResponse.success(result));
//    }

    // 스포츠 팀 상세 조회
//    @GetMapping("/teams/{teamCode}")
//    public ResponseEntity<ApiResponse<TeamQueryDto>> detailTeams(@PathVariable Long teamCode) {
//        TeamQueryDto result = sportsQueryService.detailTeams(teamCode);
//        return ResponseEntity.ok(ApiResponse.success(result));
//    }
}