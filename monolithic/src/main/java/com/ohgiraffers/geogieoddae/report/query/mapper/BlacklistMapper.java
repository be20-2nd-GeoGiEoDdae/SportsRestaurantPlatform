package com.ohgiraffers.geogieoddae.report.query.mapper;

import com.ohgiraffers.geogieoddae.report.query.dto.BlacklistQueryDto;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface BlacklistMapper {

    // 블랙리스트 전체 조회
    List<BlacklistQueryDto> selectBlacklist();
}