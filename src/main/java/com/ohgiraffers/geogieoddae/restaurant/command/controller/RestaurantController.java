package com.ohgiraffers.geogieoddae.restaurant.command.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.RestaurantDto;
import com.ohgiraffers.geogieoddae.restaurant.command.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<ApiResponse<RestaurantDto>> registerRestaurant(@RequestBody RestaurantDto restaurantDto) {
        restaurantService.registerRestaurant(restaurantDto);
        return ResponseEntity.ok(ApiResponse.success(restaurantDto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantDto>> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<ApiResponse<RestaurantDto>> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantDto restaurantDto) {
//        restaurantService.updateRestaurant(id, restaurantDto);
//        return ResponseEntity.ok(ApiResponse.success(restaurantDto));
//    }
}
