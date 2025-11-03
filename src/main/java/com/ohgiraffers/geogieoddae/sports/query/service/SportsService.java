package com.ohgiraffers.geogieoddae.sports.query.service;

import com.ohgiraffers.geogieoddae.sports.query.dto.SportQueryDto;
import com.ohgiraffers.geogieoddae.sports.query.dto.TeamQueryDto;
import com.ohgiraffers.geogieoddae.sports.query.mapper.SportsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SportsService {

    private final SportsMapper sportsMapper;

    // 스포츠 종목 목록 조회
    public List<SportQueryDto> listSports() {
        return sportsMapper.selectSportList();
    }

    // 스포츠 종목 상세 조회
    public SportQueryDto detailSport(Long sportCode) {
        SportQueryDto result = sportsMapper.selectSportByCode(sportCode);
        if (result == null) {
            throw new IllegalArgumentException("스포츠 종목이 존재하지 않습니다.: " + sportCode);
        }
        return result;
    }

    // 스포츠 팀 목록 조회 (전체)
    public List<TeamQueryDto> listTeams() {
        return sportsMapper.selectTeamList();
    }

    // 특정 스포츠 종목의 팀 목록 조회
    public List<TeamQueryDto> listTeamsBySportCode(Long sportCode) {
        return sportsMapper.selectTeamListBySportCode(sportCode);
    }

    // 스포츠 팀 상세 조회
    public TeamQueryDto detailTeam(Long teamCode) {
        TeamQueryDto result = sportsMapper.selectTeamByCode(teamCode);
        if (result == null) {
            throw new IllegalArgumentException("스포츠 팀이 존재하지 않습니다.: " + teamCode);
        }
        return result;
    }
}