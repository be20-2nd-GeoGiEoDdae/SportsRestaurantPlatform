package com.geogieoddae.mainservice.restaurant.query.controller;

import com.geogieoddae.mainservice.restaurant.query.dto.RestaurantDto;
import com.geogieoddae.mainservice.restaurant.query.service.RestaurantQueryService;
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
