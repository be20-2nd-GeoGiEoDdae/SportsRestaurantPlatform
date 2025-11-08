package com.ohgiraffers.geogieoddae.sports.query.service;

import com.ohgiraffers.geogieoddae.sports.query.dto.SportsQueryDto;
import com.ohgiraffers.geogieoddae.sports.query.dto.TeamQueryDto;
import com.ohgiraffers.geogieoddae.sports.query.mapper.SportsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SportsQueryService {

    private final SportsMapper sportsMapper;

    // 스포츠 종목 목록 조회
    public List<SportsQueryDto> listSports() {
        return sportsMapper.selectSportList();
    }

    // 스포츠 종목 상세 조회
    public SportsQueryDto detailSports(Long sportCode) {
        SportsQueryDto result = sportsMapper.selectSportByCode(sportCode);
        if (result == null) {
            throw new IllegalArgumentException("스포츠 종목이 존재하지 않습니다.");
        }
        return result;
    }

    // 스포츠 팀 목록 조회 (전체)
    public List<TeamQueryDto> listTeams() {
        return sportsMapper.selectTeamList();
    }

    // 특정 스포츠 종목의 팀 목록 조회
    public List<TeamQueryDto> listTeamsBySport(Long sportCode) {
        return sportsMapper.selectTeamListBySportCode(sportCode);
    }

    // 스포츠 팀 상세 조회
    public TeamQueryDto detailTeams(Long teamCode) {
        TeamQueryDto result = sportsMapper.selectTeamByCode(teamCode);
        if (result == null) {
            throw new IllegalArgumentException("스포츠 팀이 존재하지 않습니다.");
        }
        return result;
    }
}