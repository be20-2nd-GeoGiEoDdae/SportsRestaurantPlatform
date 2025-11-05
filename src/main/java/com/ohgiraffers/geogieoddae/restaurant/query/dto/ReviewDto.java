package com.ohgiraffers.geogieoddae.restaurant.query.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class ReviewDto {
    @NotNull
    private String reviewTitle;
    @NotNull
    private String reviewBody;
    @NotNull
    private Integer reviewScore;
    @NotNull
    private String restaurantName;
    @NotNull
    private String userName;
    @NotNull
    private String pictures;
}


