package com.ohgiraffers.geogieoddae.sports.command.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.sports.command.dto.competition.CompetitionCreateRequest;
import com.ohgiraffers.geogieoddae.sports.command.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/sports/competitions")
@RequiredArgsConstructor
public class CompetitionCommandController {

    private final CompetitionService competitionService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createCompetition(@RequestBody CompetitionCreateRequest request) {
        competitionService.createCompetition(request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @DeleteMapping("/{competitionId}")
    public ResponseEntity<ApiResponse<?>> deleteCompetition(@PathVariable Long competitionId) {
        competitionService.deleteCompetition(competitionId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
