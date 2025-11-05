package com.ohgiraffers.geogieoddae.restaurant.query.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RestaurantDto {
    @NotNull
    private Long restaurantCode;
    @NotNull
    private String restaurantName;
    @NotNull
    private String restaurantLocation;
    @NotNull
    private String restaurantCategory;
    @NotNull
    private Integer restaurantPeopleNumber;
    @NotNull
    private String restaurantContents;
    @NotNull
    private Double restaurantScore;
    @NotNull
    private String createdAt;
}
