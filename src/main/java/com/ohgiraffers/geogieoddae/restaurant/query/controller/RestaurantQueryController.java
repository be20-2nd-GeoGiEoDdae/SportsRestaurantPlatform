package com.ohgiraffers.geogieoddae.restaurant.query.controller;

import com.ohgiraffers.geogieoddae.restaurant.query.dto.RestaurantDto;
import com.ohgiraffers.geogieoddae.restaurant.query.service.RestaurantQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "가게 조회 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class RestaurantQueryController {

    private final RestaurantQueryService restaurantQueryService;

    @GetMapping("/list")
    public ResponseEntity<List<RestaurantDto>> getRestaurantList(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sort) {

        List<RestaurantDto> result = restaurantQueryService.getRestaurantList(category, sort);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/filter")
    public ResponseEntity<List<RestaurantDto>> filterRestaurants(
            @RequestParam(required = false) String restaurant_name,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category
    ) {
        List<RestaurantDto> restaurants = restaurantQueryService.findRestaurantList(restaurant_name, keyword, category);
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{restaurantCode}")
    public ResponseEntity<RestaurantDto> getRestaurantDetail(@PathVariable Long restaurantCode) {
        RestaurantDto detail = restaurantQueryService.getRestaurantDetail(restaurantCode);
        return ResponseEntity.ok(detail);
    }
}
