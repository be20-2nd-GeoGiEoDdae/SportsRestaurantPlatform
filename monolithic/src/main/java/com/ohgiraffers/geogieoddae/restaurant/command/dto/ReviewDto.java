package com.ohgiraffers.geogieoddae.restaurant.command.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReviewDto {
    @NotBlank
    private String reviewTitle;
    @NotBlank
    private String reviewBody;
    @NotBlank
    private Integer reviewScore;
    @NotBlank
    private Long restaurantCode;
    @NotBlank
    private Long userCode;
    @NotBlank
    private List<String> pictureUrls;
}


