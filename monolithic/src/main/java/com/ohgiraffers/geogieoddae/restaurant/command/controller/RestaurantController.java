package com.ohgiraffers.geogieoddae.restaurant.command.controller;

import com.ohgiraffers.geogieoddae.global.apikey.GoogleGeocodingService;
import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.RestaurantCreateDto;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.RestaurantDistanceResponse;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.RestaurantDto;
import com.ohgiraffers.geogieoddae.restaurant.command.service.RestaurantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "가게 관리 api")
@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
@Slf4j
public class RestaurantController {

    public final RestaurantService restaurantService;
    private final GoogleGeocodingService googleGeocodingService;

//    @PreAuthorize("hasAuthority('ROLE_ENTREPRENEUR')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<RestaurantCreateDto>> registerRestaurant(
            @RequestPart("restaurant") RestaurantCreateDto restaurantDto,
            @RequestPart(value = "pictures", required = false) List<MultipartFile> pictures
    ) throws IOException {

        restaurantService.registerRestaurant(restaurantDto, pictures);
        return ResponseEntity.ok(ApiResponse.success(restaurantDto));
    }

//        @PreAuthorize("hasRole('ENTREPRENEUR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<RestaurantDto>> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PatchMapping(value = "/{restaurantId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<?>> updateRestaurant(
            @PathVariable Long restaurantId,
            @RequestPart("restaurant") RestaurantCreateDto restaurantDto,
            @RequestPart(value = "pictures", required = false) List<MultipartFile> pictures
    ) throws IOException {

        restaurantService.updateRestaurant(restaurantId, restaurantDto, pictures);
        return ResponseEntity.ok(ApiResponse.success("식당 정보 수정 완료"));
    }
    @GetMapping("/nearby/address")
    public ResponseEntity<ApiResponse<List<RestaurantDistanceResponse>>> getNearbyRestaurantsByAddress(
            @RequestParam String address
    ) {
        // 1️⃣ 주소를 위도/경도로 변환
        double[] coords = googleGeocodingService.getCoordinates(address);

        double latitude = coords[0];
        double longitude = coords[1];

        log.info("📍 입력 주소 '{}' → 위도: {}, 경도: {}", address, latitude, longitude);

        // 2️⃣ 거리순 식당 조회
        List<RestaurantDistanceResponse> result = restaurantService.getRestaurantsSortedByDistance(latitude, longitude);
        return ResponseEntity.ok(ApiResponse.success(result));
    }


}
