package com.ohgiraffers.geogieoddae.restaurant.query.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.restaurant.query.dto.RestaurantDto;
import com.ohgiraffers.geogieoddae.restaurant.query.service.RestaurantQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Slf4j
@Tag(name = "가게 조회 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class RestaurantQueryController {

    private final RestaurantQueryService restaurantQueryService;
    @GetMapping("/list")
    public ResponseEntity<List<RestaurantDto>> getRestaurantList(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String category,

            // ⭐ 변경: 단일 keyword → 키워드 배열로!
            @RequestParam(required = false) List<String> keywords,

            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Double userLat,
            @RequestParam(required = false) Double userLng,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        List<RestaurantDto> result =
                restaurantQueryService.getRestaurantList(userId, category, keywords, sort, userLat, userLng, page, size);

        return ResponseEntity.ok(result);
    }



    @GetMapping("/filter")
    public ResponseEntity<List<RestaurantDto>> filterRestaurants(
            @RequestParam(required = false) String restaurant_name,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category
    ) {
        return ResponseEntity.ok(
                restaurantQueryService.findRestaurantList(restaurant_name, keyword, category)
        );
    }

    @GetMapping("/{restaurantCode}")
    public ResponseEntity<RestaurantDto> getRestaurantDetail(@PathVariable Long restaurantCode) {
        return ResponseEntity.ok(
                restaurantQueryService.getRestaurantDetail(restaurantCode)
        );
    }

    @GetMapping("/{restaurantId}/images")
    public ResponseEntity<ApiResponse<List<String>>> getRestaurantImages(
            @PathVariable Long restaurantId
    ) {
        List<String> images = restaurantQueryService.getRestaurantImages(restaurantId);
        return ResponseEntity.ok(ApiResponse.success(images));
    }
}
