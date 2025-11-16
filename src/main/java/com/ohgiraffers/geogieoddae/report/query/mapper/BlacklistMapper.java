package com.ohgiraffers.geogieoddae.report.query.mapper;

import com.ohgiraffers.geogieoddae.report.query.dto.BlacklistQueryDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BlacklistQueryMapper {

    // 블랙리스트 전체 조회
    List<BlacklistQueryDto> selectBlacklist();
}