package com.ohgiraffers.geogieoddae.restaurant.command.service;

import com.ohgiraffers.geogieoddae.restaurant.command.dto.ReviewDto;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    //리뷰 등록
    void registerReview(ReviewDto reviewDto, Long userId, Long restaurantId);
    //리뷰 삭제
    void deleteReviewAdmin(Long reviewId);
//    //작성한 리뷰 삭제
    void deleteReviewUser(Long reviewId);
    void updateReview(Long reviewId, ReviewDto reviewDto);
}
