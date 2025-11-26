package com.ohgiraffers.geogieoddae.restaurant.query.service;

import com.ohgiraffers.geogieoddae.restaurant.query.dto.RestaurantDto;
import com.ohgiraffers.geogieoddae.restaurant.query.mapper.RestaurantMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantQueryService {

    private final RestaurantMapper restaurantMapper;

    public List<RestaurantDto> getRestaurantList(
            Long userId,
            String category,
            List<String> keywords,   // ⭐ 배열로 받음
            String sort,
            Double userLat,
            Double userLng,
            int page,
            int size
    ) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("category", category);
        params.put("keywords", keywords); // ⭐ 배열 그대로 넘김
        params.put("sort", sort);
        params.put("userLat", userLat);
        params.put("userLng", userLng);
        params.put("offset", (page - 1) * size);
        params.put("size", size);

        return restaurantMapper.getRestaurantList(params);
    }



    // 검색
    public List<RestaurantDto> findRestaurantList(String restaurantName, String keyword, String category) {
        Map<String, Object> params = new HashMap<>();
        params.put("restaurant_name", restaurantName);
        params.put("keyword", keyword);
        params.put("category", category);

        return restaurantMapper.findRestaurantList(params);
    }

    // 상세 조회
    public RestaurantDto getRestaurantDetail(Long restaurantCode) {
        return restaurantMapper.findRestaurantDetail(restaurantCode);
    }

    public List<String> getRestaurantImages(Long restaurantCode) {
        return restaurantMapper.findRestaurantImages(restaurantCode);
    }
}
