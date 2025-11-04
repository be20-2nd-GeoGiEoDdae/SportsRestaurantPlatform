package com.ohgiraffers.geogieoddae.restaurant.command.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.ReviewDto;
import com.ohgiraffers.geogieoddae.restaurant.command.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;


    @PostMapping("/{userId}/{restaurantId}")
    public ResponseEntity<ApiResponse<ReviewDto>> registerReview(@RequestBody ReviewDto reviewDto,
                                                                 @PathVariable Long userId,
                                                                 @PathVariable Long restaurantId){
        reviewService.registerReview(reviewDto,userId,restaurantId);
        return ResponseEntity.ok(ApiResponse.success(reviewDto));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<?>> deleteReview(@PathVariable Long reviewId){
        reviewService.deleteReviewUser(reviewId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

//    @DeleteMapping("/{reviewId}")
//    public ResponseEntity<ApiResponse<?>> deleteReview(@PathVariable Long reviewId){
//        reviewService.deleteReviewUser(reviewId);
//        return ResponseEntity.ok(ApiResponse.success(null));
//    }
    @PatchMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<ReviewDto>> updateReview(@PathVariable Long reviewId, @RequestBody ReviewDto reviewDto){
        reviewService.updateReview(reviewId,reviewDto);
        return ResponseEntity.ok(ApiResponse.success(reviewDto));
    }

}
