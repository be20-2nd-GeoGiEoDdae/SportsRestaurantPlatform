package com.ohgiraffers.geogieoddae.restaurant.command.service.impl;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.ReviewDto;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.review.ReviewEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.restaurant.RestaurantRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.review.ReviewRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.service.ReviewService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    @Override
    public void registerReview(ReviewDto reviewDto, Long restaurantId, Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 식당입니다."));
        ReviewEntity review = ReviewEntity.builder()
                .reviewTitle(reviewDto.getReviewTitle())
                .reviewBody(reviewDto.getReviewBody())
                .reviewScore(reviewDto.getReviewScore())
                .member(user)
                .restaurant(restaurant)
                .build();

        reviewRepository.save(review);

    }

    @Override
    public void deleteReviewAdmin(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public void deleteReviewUser(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
    @Transactional
    @Override
    public void updateReview(Long reviewId, ReviewDto reviewDto) {
        ReviewEntity review = reviewRepository.findById(reviewId).orElse(new ReviewEntity());
        review.setReviewTitle(reviewDto.getReviewTitle());
        review.setReviewBody(reviewDto.getReviewBody());
        review.setReviewScore(reviewDto.getReviewScore());

        if (reviewDto.getRestaurantCode() != null) {
            RestaurantEntity restaurant = restaurantRepository.findById(reviewDto.getRestaurantCode())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 식당입니다."));
            review.setRestaurant(restaurant);
        }

        if (reviewDto.getUserCode() != null) {
            UserEntity user = userRepository.findById(reviewDto.getUserCode())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
            review.setMember(user);
        }

    }
}
