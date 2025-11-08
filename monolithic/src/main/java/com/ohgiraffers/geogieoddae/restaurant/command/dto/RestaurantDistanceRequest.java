package com.ohgiraffers.geogieoddae.restaurant.command.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDistanceRequest {
    private String restaurantName;
    private String address;
}
