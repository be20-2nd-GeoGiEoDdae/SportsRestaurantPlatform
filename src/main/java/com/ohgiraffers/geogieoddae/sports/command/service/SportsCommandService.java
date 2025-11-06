package com.ohgiraffers.geogieoddae.sports.command.service;

import com.ohgiraffers.geogieoddae.sports.command.dto.SportsRequestDto;
import com.ohgiraffers.geogieoddae.sports.command.dto.SportsResponseDto;
import com.ohgiraffers.geogieoddae.sports.command.dto.TeamRequestDto;
import com.ohgiraffers.geogieoddae.sports.command.dto.TeamResponseDto;
import com.ohgiraffers.geogieoddae.sports.command.entity.SportsEntity;
import com.ohgiraffers.geogieoddae.sports.command.entity.TeamEntity;
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

    // 스포츠 종목 등록
    public void createSport(SportsRequestDto request) {
        SportsEntity sportsEntity = SportsEntity.builder()
                .sportName(request.getSportName())
                .sportDescription(request.getSportDescription())
                .build();

        sportsRepository.save(sportsEntity);
    }

    // 스포츠 종목 수정
    public SportsResponseDto updateSport(SportsRequestDto request, Long sportCode) {
        SportsEntity entity = sportsRepository.findById(sportCode)
                .orElseThrow(() -> new IllegalArgumentException("스포츠 종목이 존재하지 않습니다.: " + sportCode));

        entity.setSportName(request.getSportName());
        entity.setSportDescription(request.getSportDescription());

        return SportsResponseDto.builder()
                .sportCode(entity.getSportCode())
                .sportName(entity.getSportName())
                .sportDescription(entity.getSportDescription())
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
    public void createTeam(TeamRequestDto request) {
        SportsEntity sportsEntity = sportsRepository.findById(request.getSportCode())
                .orElseThrow(() -> new IllegalArgumentException("스포츠 종목이 존재하지 않습니다.: " + request.getSportCode()));

        TeamEntity teamEntity = TeamEntity.builder()
                .teamName(request.getTeamName())
                .teamDescription(request.getTeamDescription())
                .sport(sportsEntity)
                .build();

        teamRepository.save(teamEntity);
    }

    // 스포츠 팀 수정
    public TeamResponseDto updateTeam(TeamRequestDto request, Long teamCode) {
        TeamEntity entity = teamRepository.findById(teamCode)
                .orElseThrow(() -> new IllegalArgumentException("스포츠 팀이 존재하지 않습니다.: " + teamCode));

        String name = request.getTeamName();
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("팀 이름은 비어 있을 수 없습니다.");
        }
        entity.setTeamName(name);

        return TeamResponseDto.builder()
                .teamCode(entity.getTeamCode())
                .sportCode(entity.getSport().getSportCode())
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