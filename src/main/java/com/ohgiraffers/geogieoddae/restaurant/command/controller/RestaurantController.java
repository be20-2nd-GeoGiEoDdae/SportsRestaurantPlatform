package com.ohgiraffers.geogieoddae.restaurant.command.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.RestaurantDto;
import com.ohgiraffers.geogieoddae.restaurant.command.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    public final RestaurantService restaurantService;
//    @PreAuthorize("hasRole('ENTREPRENEUR')")
    @PostMapping
    public ResponseEntity<ApiResponse<RestaurantDto>> registerRestaurant(@RequestBody RestaurantDto restaurantDto) {
        restaurantService.registerRestaurant(restaurantDto);
        return ResponseEntity.ok(ApiResponse.success(restaurantDto));
    }
    //    @PreAuthorize("hasRole('ENTREPRENEUR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantDto>> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
    //    @PreAuthorize("hasRole('ENTREPRENEUR')")
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantDto>> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantDto restaurantDto) {
        restaurantService.updateRestaurant(id, restaurantDto);
        return ResponseEntity.ok(ApiResponse.success(restaurantDto));
    }
}
