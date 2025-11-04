package com.ohgiraffers.geogieoddae.restaurant.query.mapper;


import com.ohgiraffers.geogieoddae.restaurant.query.dto.ReviewDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
    //  특정 식당의 리뷰 목록 조회
    List<ReviewDto> findReviewsByRestaurantId(@Param("restaurantCode") Long restaurantCode);

    //  특정 사용자의 리뷰 목록 조회
    List<ReviewDto> findReviewsByUserId(@Param("userCode") Long userCode);

    // 식당의 리뷰를 별점 순으로 조회
    List<ReviewDto> findReviewsByScore(@Param("restaurantCode") Long restaurantCode,  @Param("order") String order);


}
