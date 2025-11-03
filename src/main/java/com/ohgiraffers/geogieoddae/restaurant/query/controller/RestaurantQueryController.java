package com.ohgiraffers.geogieoddae.restaurant.query.controller;

import com.ohgiraffers.geogieoddae.restaurant.query.dto.RestaurantDto;
import com.ohgiraffers.geogieoddae.restaurant.query.service.RestaurantQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
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
    public ResponseEntity<List<RestaurantDto>> findRestaurants(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category) {

        List<RestaurantDto> result = restaurantQueryService.findRestaurantList(keyword, category);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{restaurantCode}")
    public ResponseEntity<RestaurantDto> getRestaurantDetail(@PathVariable Long restaurantCode) {
        RestaurantDto detail = restaurantQueryService.getRestaurantDetail(restaurantCode);
        return ResponseEntity.ok(detail);
    }
}
