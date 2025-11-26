package com.ohgiraffers.geogieoddae.sports.command.controller;

import com.ohgiraffers.geogieoddae.sports.command.dto.league.LeagueCreateRequest;
import com.ohgiraffers.geogieoddae.sports.command.service.LeagueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/sports/leagues")
@RequiredArgsConstructor
public class LeagueCommandController {

    private final LeagueService leagueService;

    @PostMapping
    public ResponseEntity<Long> createLeague(@RequestBody LeagueCreateRequest request) {
        return ResponseEntity.ok(leagueService.createLeague(request));
    }

    @DeleteMapping("/{leagueId}")
    public ResponseEntity<Void> deleteLeague(@PathVariable Long leagueId) {
        leagueService.deleteLeague(leagueId);
        return ResponseEntity.ok().build();
    }
}