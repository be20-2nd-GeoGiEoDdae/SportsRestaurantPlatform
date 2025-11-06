package com.ohgiraffers.geogieoddae.restaurant.command.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.RestaurantDto;
import com.ohgiraffers.geogieoddae.restaurant.command.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    public final RestaurantService restaurantService;

    @PreAuthorize("hasAuthority('ROLE_ENTREPRENEUR')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<RestaurantDto>> registerRestaurant(@RequestPart("restaurant") RestaurantDto restaurantDto,
                                                                         @RequestPart(value = "pictures", required = false) List<MultipartFile> pictures) throws IOException {
        restaurantService.registerRestaurant(restaurantDto, pictures);
        return ResponseEntity.ok(ApiResponse.success(restaurantDto));
    }

        @PreAuthorize("hasRole('ENTREPRENEUR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantDto>> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PatchMapping(value = "/{restaurantId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<?>> updateRestaurant(
            @PathVariable Long restaurantId,
            @RequestPart("restaurant") RestaurantDto restaurantDto,
            @RequestPart(value = "pictures", required = false) List<MultipartFile> pictures
    ) throws IOException {

        restaurantService.updateRestaurant(restaurantId, restaurantDto, pictures);
        return ResponseEntity.ok(ApiResponse.success("식당 정보 수정 완료"));
    }

}
