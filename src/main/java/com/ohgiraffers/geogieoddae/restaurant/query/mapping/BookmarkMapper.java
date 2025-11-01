package com.ohgiraffers.geogieoddae.restaurant.query.mapping;

import com.ohgiraffers.geogieoddae.restaurant.query.dto.ReviewDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface BookmarkMapper {
    List<Map<String, Object>> findBookmarksByUserId(@Param("userCode") Long userCode);
}
