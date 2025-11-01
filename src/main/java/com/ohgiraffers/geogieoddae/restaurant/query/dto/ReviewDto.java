package com.ohgiraffers.geogieoddae.restaurant.query.dto;

import lombok.Getter;

@Getter
public class ReviewDto {
    private Long reviewCode;
    private String reviewTitle;
    private String reviewBody;
    private Integer reviewScore;
    private Long restaurantCode;
    private Long userCode;
//    private List<String> pictures;
}


