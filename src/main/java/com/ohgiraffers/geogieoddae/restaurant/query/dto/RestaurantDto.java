package com.ohgiraffers.geogieoddae.restaurant.query.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RestaurantDto {

    private Long restaurantCode;
    private String restaurantName;
    private String restaurantLocation;
    private String restaurantCategory;
    private Integer restaurantPeopleNumber;
    private String restaurantContents;
    private Double restaurantScore;
    private String pictureUrls;
    private String keywords;
    private String createdAt;
}
