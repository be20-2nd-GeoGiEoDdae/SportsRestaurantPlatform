package com.geogieoddae.mainservice.restaurant.query.controller;

import com.geogieoddae.mainservice.restaurant.query.dto.ReviewDto;
import com.geogieoddae.mainservice.restaurant.query.service.ReviewQueryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewQueryController {

    private final ReviewQueryService reviewService;

    @GetMapping("/restaurant/{restaurantCode}")
    public ResponseEntity<List<ReviewDto>> getReviewsByRestaurant(@PathVariable Long restaurantCode) {
        return ResponseEntity.ok(reviewService.getReviewsByRestaurant(restaurantCode));
    }

    @GetMapping("/user/{userCode}")
    public ResponseEntity<List<ReviewDto>> getReviewsByUser(@PathVariable Long userCode) {
        return ResponseEntity.ok(reviewService.getReviewsByUser(userCode));
    }
    @GetMapping("/restaurant/{restaurantCode}/{order}")
    public ResponseEntity<List<ReviewDto>> getReviewsByScore(@PathVariable Long restaurantCode, @PathVariable String order){
        return ResponseEntity.ok(reviewService.getReviewsByScore(restaurantCode,order));
    }
}
