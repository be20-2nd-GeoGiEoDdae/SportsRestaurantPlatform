package com.geogieoddae.mainservice.restaurant.query.service;

import com.geogieoddae.mainservice.restaurant.query.dto.ReviewDto;
import com.geogieoddae.mainservice.restaurant.query.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewMapper reviewMapper;


    public List<ReviewDto> getReviewsByRestaurant(Long restaurantCode) {

        return reviewMapper.findReviewsByRestaurantId(restaurantCode);
    }

    public List<ReviewDto> getReviewsByUser(Long userCode) {
        List<ReviewDto> reviews = reviewMapper.findReviewsByUserId(userCode);
        reviews.forEach(System.out::println);
        return reviewMapper.findReviewsByUserId(userCode);
    }

    public List<ReviewDto> getReviewsByScore(Long restaurantCode, String order){{
        return reviewMapper.findReviewsByScore(restaurantCode,order);
    }}


}
