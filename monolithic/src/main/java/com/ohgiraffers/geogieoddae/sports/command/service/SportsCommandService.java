package com.ohgiraffers.geogieoddae.sports.command.service;

import com.ohgiraffers.geogieoddae.sports.command.dto.SportsRequestDto;
import com.ohgiraffers.geogieoddae.sports.command.dto.SportsResponseDto;
import com.ohgiraffers.geogieoddae.sports.command.dto.TeamRequestDto;
import com.ohgiraffers.geogieoddae.sports.command.dto.TeamResponseDto;
import com.ohgiraffers.geogieoddae.sports.command.entity.LeagueEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.SportsEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.TeamEntity;
import com.ohgiraffers.geogieoddae.sports.command.repository.LeagueRepository;
import com.ohgiraffers.geogieoddae.sports.command.repository.SportsRepository;
import com.ohgiraffers.geogieoddae.sports.command.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SportsCommandService {

    private final SportsRepository sportsRepository;
    private final TeamRepository teamRepository;
    private final LeagueRepository leagueRepository;
    // 스포츠 종목 등록
    public void createSport(SportsRequestDto request) {
        SportsEntity sportsEntity = SportsEntity.builder()
                .sportName(request.getSportName())
                .build();

        sportsRepository.save(sportsEntity);
    }

    // 스포츠 종목 수정
    public SportsResponseDto updateSport(SportsRequestDto request, Long sportCode) {
        SportsEntity entity = sportsRepository.findById(sportCode)
                .orElseThrow(() -> new IllegalArgumentException("스포츠 종목이 존재하지 않습니다.: " + sportCode));

        entity.setSportName(request.getSportName());

        return SportsResponseDto.builder()
                .sportCode(entity.getSportCode())
                .sportName(entity.getSportName())
                .build();
    }

    // 스포츠 종목 삭제
    public void deleteSport(Long sportCode) {
        if (!sportsRepository.existsById(sportCode)) {
            throw new IllegalArgumentException("스포츠 종목이 존재하지 않습니다." + sportCode);
        }
        sportsRepository.deleteById(sportCode);
    }

    // 스포츠 팀 등록
    public Long createTeam(TeamRequestDto request) {
        LeagueEntity league = leagueRepository.findById(request.getLeagueId())
                .orElseThrow(() -> new IllegalArgumentException("리그 없음"));

        TeamEntity team = TeamEntity.builder()
                .teamName(request.getName())
                .league(league)
                .build();

        teamRepository.save(team);
        return team.getTeamCode();
    }

    // 스포츠 팀 수정
    public TeamResponseDto updateTeam(Long id, TeamRequestDto request) {
        TeamEntity team = teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("팀 없음"));

        team.update(request.getName());

        return TeamResponseDto.builder()
                .id(team.getTeamCode())
                .name(team.getTeamName())
                .leagueId(team.getLeague().getId())
                .build();
    }

    // 스포츠 팀 삭제
    public void deleteTeam(Long teamCode) {
        if (!teamRepository.existsById(teamCode)) {
            throw new IllegalArgumentException("스포츠 팀이 존재하지 않습니다.: " + teamCode);
        }
        teamRepository.deleteById(teamCode);
    }
}