package com.geogieoddae.mainservice.restaurant.query.service;

import com.geogieoddae.mainservice.restaurant.query.dto.RestaurantDto;
import com.geogieoddae.mainservice.restaurant.query.mapper.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RestaurantQueryService {

    private final RestaurantMapper restaurantMapper;

    //목록 조회
    public List<RestaurantDto> getRestaurantList(String category, String sort) {
        Map<String, Object> params = new HashMap<>();
        params.put("category", category);
        params.put("sort", sort);
        return restaurantMapper.getRestaurantList(params);
    }

    //    가게명 + 키워드 검색 + 카테고리 필터
    public List<RestaurantDto> findRestaurantList(String restaurantName, String keyword, String category) {
        Map<String, Object> params = new HashMap<>();
        params.put("restaurant_name", restaurantName);
        params.put("keyword", keyword);
        params.put("category", category);

        return restaurantMapper.findRestaurantList(params);
    }

    //상세 조회
    public RestaurantDto getRestaurantDetail(Long restaurantCode) {
        return restaurantMapper.findRestaurantDetail(restaurantCode);
    }
}
