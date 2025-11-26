package com.ohgiraffers.geogieoddae.sports.query.service;

import com.ohgiraffers.geogieoddae.sports.command.entity.LeagueEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.TeamEntity;
import com.ohgiraffers.geogieoddae.sports.command.repository.LeagueRepository;
import com.ohgiraffers.geogieoddae.sports.command.repository.TeamRepository;
import com.ohgiraffers.geogieoddae.sports.query.dto.TeamQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamQueryService {

    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;

    public Long create(Long leagueId, String name) {
        LeagueEntity league = leagueRepository.findById(leagueId).orElseThrow();
        TeamEntity team = new TeamEntity(name, league);
        teamRepository.save(team);
        return team.getTeamCode();
    }
    public TeamQueryDto findOne(Long teamId) {
        TeamEntity t = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found: " + teamId));

        return new TeamQueryDto(
                t.getTeamCode(),
                t.getTeamName()
        );
    }
    public List<TeamQueryDto> findByLeague(Long leagueId) {
        return teamRepository.findByLeagueId(leagueId)
                .stream()
                .map(t -> new TeamQueryDto(
                        t.getTeamCode(),
                        t.getTeamName()
                ))
                .toList();
    }
}
