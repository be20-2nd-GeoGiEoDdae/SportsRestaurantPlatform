package com.geogieoddae.mainservice.sports.query.mapper;

import com.geogieoddae.mainservice.sports.query.dto.SportsQueryDto;
import com.geogieoddae.mainservice.sports.query.dto.TeamQueryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SportsMapper {

    // 스포츠 종목 목록 조회
    List<SportsQueryDto> selectSportList();

    // 스포츠 종목 상세 조회
    SportsQueryDto selectSportByCode(Long sportCode);

    // 스포츠 팀 목록 조회 (전체)
    List<TeamQueryDto> selectTeamList();

    // 특정 스포츠 종목의 팀 목록 조회
    List<TeamQueryDto> selectTeamListBySportCode(Long sportCode);

    // 스포츠 팀 상세 조회
    TeamQueryDto selectTeamByCode(Long teamCode);
}
