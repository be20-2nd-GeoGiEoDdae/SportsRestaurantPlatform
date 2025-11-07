package com.ohgiraffers.geogieoddae.restaurant.command.dto;

import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RestaurantDistanceResponse {
    private Long restaurantCode;
    private String restaurantName;
    private String location;
    private double latitude;
    private double longitude;
    private String distance;

}
