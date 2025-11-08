package com.ohgiraffers.geogieoddae.restaurant.query.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookmarkMapper {
    List<Map<String, Object>> findBookmarksByUserId(@Param("userCode") Long userCode);
}
