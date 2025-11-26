package com.ohgiraffers.geogieoddae.sports.query.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.sports.query.service.TeamQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leagues/{leagueId}/teams")
@RequiredArgsConstructor
public class TeamQueryController {

    private final TeamQueryService teamService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getTeams(@PathVariable Long leagueId) {
        return ResponseEntity.ok(ApiResponse.success(
                teamService.findByLeague(leagueId)
        ));
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<ApiResponse<?>> getTeam(
            @PathVariable Long leagueId,
            @PathVariable Long teamId
    ) {
        return ResponseEntity.ok(ApiResponse.success(
                teamService.findOne(teamId)
        ));
    }
}
