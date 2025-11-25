package com.ohgiraffers.geogieoddae.sports.query.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.sports.command.service.CompetitionService;
import com.ohgiraffers.geogieoddae.sports.query.service.CompetitionQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sports/{sportId}/competitions")
@RequiredArgsConstructor
public class CompetitionQueryController {

    private final CompetitionQueryService competitionService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getCompetitions(@PathVariable Long sportId) {
        return ResponseEntity.ok(ApiResponse.success(
                competitionService.findBySport(sportId)
        ));
    }

    @GetMapping("/{competitionId}")
    public ResponseEntity<ApiResponse<?>> getCompetition(
            @PathVariable Long sportId,
            @PathVariable Long competitionId
    ) {
        return ResponseEntity.ok(ApiResponse.success(
                competitionService.findOne(competitionId)
        ));
    }
}
