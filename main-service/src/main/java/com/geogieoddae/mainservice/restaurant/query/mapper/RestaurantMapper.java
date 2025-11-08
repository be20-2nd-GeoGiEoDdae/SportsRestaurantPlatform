package com.geogieoddae.mainservice.restaurant.query.mapper;

import com.geogieoddae.mainservice.restaurant.query.dto.RestaurantDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RestaurantMapper {

    // 전체 목록 조회
    List<RestaurantDto> getRestaurantList(Map<String, Object> params);


    // 가게명 + 키워드 검색
    List<RestaurantDto> findRestaurantList(Map<String, Object> params);

    // 상세 조회
    RestaurantDto findRestaurantDetail(@Param("restaurantCode") Long restaurantCode);
}
