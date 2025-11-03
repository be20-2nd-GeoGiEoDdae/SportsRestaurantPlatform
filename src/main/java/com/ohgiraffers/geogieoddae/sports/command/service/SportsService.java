package com.ohgiraffers.geogieoddae.sports.command.service;

import com.ohgiraffers.geogieoddae.sports.command.dto.SportRequestDto;
import com.ohgiraffers.geogieoddae.sports.command.dto.SportResponseDto;
import com.ohgiraffers.geogieoddae.sports.command.dto.TeamRequestDto;
import com.ohgiraffers.geogieoddae.sports.command.dto.TeamResponseDto;
import com.ohgiraffers.geogieoddae.sports.command.entity.SportEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.TeamEntity;
import com.ohgiraffers.geogieoddae.sports.command.repository.SportsRepository;
import com.ohgiraffers.geogieoddae.sports.command.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SportsService {

    private final SportsRepository sportsRepository;
    private final TeamRepository teamRepository;

    // 스포츠 종목 등록
    public void createSport(SportRequestDto request) {
        SportEntity sportEntity = SportEntity.builder()
                .sportName(request.getSportName())
                .sportDescription(request.getSportDescription())
                .build();

        sportsRepository.save(sportEntity);
    }

    // 스포츠 종목 수정
    public SportResponseDto updateSport(SportRequestDto request, Long sportCode) {
        SportEntity entity = sportsRepository.findById(sportCode)
                .orElseThrow(() -> new IllegalArgumentException("스포츠 종목이 존재하지 않습니다.: " + sportCode));

        entity.setSportName(request.getSportName());
        entity.setSportDescription(request.getSportDescription());

        return SportResponseDto.builder()
                .sportCode(entity.getSportCode())
                .sportName(entity.getSportName())
                .sportDescription(entity.getSportDescription())
                .build();
    }

    // 스포츠 종목 삭제
    public void deleteSport(Long sportCode) {
        if (!sportsRepository.existsById(sportCode)) {
            throw new IllegalArgumentException("스포츠 종목이 존재하지 않습니다.: " + sportCode);
        }
        sportsRepository.deleteById(sportCode);
    }

    // 스포츠 팀 등록
    public void createTeam(TeamRequestDto request) {
        SportEntity sportEntity = sportsRepository.findById(request.getSportCode())
                .orElseThrow(() -> new IllegalArgumentException("스포츠 종목이 존재하지 않습니다.: " + request.getSportCode()));

        TeamEntity teamEntity = TeamEntity.builder()
                .teamName(request.getTeamName())
                .teamDescription(request.getTeamDescription())
                .sport(sportEntity)
                .build();

        teamRepository.save(teamEntity);
    }

    // 스포츠 팀 수정
    public TeamResponseDto updateTeam(TeamRequestDto request, Long teamCode) {
        TeamEntity entity = teamRepository.findById(teamCode)
                .orElseThrow(() -> new IllegalArgumentException("스포츠 팀이 존재하지 않습니다.: " + teamCode));

        entity.setTeamName(request.getTeamName());
        entity.setTeamDescription(request.getTeamDescription());

        return TeamResponseDto.builder()
                .teamCode(entity.getTeamCode())
                .teamName(entity.getTeamName())
                .teamDescription(entity.getTeamDescription())
                .sportCode(entity.getSport().getSportCode())
                .sportName(entity.getSport().getSportName())
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