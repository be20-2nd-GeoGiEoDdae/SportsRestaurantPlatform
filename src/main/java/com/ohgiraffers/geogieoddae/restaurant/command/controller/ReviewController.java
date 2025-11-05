package com.ohgiraffers.geogieoddae.restaurant.command.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.ReviewDto;
import com.ohgiraffers.geogieoddae.restaurant.command.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //리뷰 등록
    @PostMapping(
            value = "/{userId}/{restaurantId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApiResponse<ReviewDto>> registerReview(
            @PathVariable Long userId,
            @PathVariable Long restaurantId,
            @RequestPart("review") ReviewDto reviewDto, //
            @RequestPart(value = "pictures", required = false) List<MultipartFile> pictures //
    ) throws IOException {

        reviewService.registerReview(reviewDto, restaurantId, userId, pictures);
        return ResponseEntity.ok(ApiResponse.success(reviewDto));
    }

    //리뷰 수정
    @PatchMapping(
            value = "/{reviewId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApiResponse<ReviewDto>> updateReview(
            @PathVariable Long reviewId,
            @RequestPart("review") ReviewDto reviewDto,
            @RequestPart(value = "pictures", required = false) List<MultipartFile> pictures
    ) throws IOException {

        reviewService.updateReview(reviewId, reviewDto, pictures);
        return ResponseEntity.ok(ApiResponse.success(reviewDto));
    }


    //리뷰 삭제(사용자)
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<?>> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReviewUser(reviewId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
    // 리뷰 삭제 (관리자)
//    @DeleteMapping("/{reviewId}")
//    public ResponseEntity<ApiResponse<?>> deleteReview(@PathVariable Long reviewId) {
//         reviewService.deleteReviewUser(reviewId);
//          return ResponseEntity.ok(ApiResponse.success(null)); // }
//    }
    }
