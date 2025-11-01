package com.ohgiraffers.geogieoddae.restaurant.command.dto;

import lombok.Getter;

import java.util.List;

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


