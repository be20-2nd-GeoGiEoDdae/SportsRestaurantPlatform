package com.ohgiraffers.geogieoddae.restaurant.query.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class ReviewDto {
    private String reviewTitle;
    private String reviewBody;
    private Integer reviewScore;
    private String restaurantName;
    private String userName;
    private String picture;   //
}


