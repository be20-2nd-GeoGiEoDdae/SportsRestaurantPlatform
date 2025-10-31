package com.ohgiraffers.geogieoddae.restaurant.command.service;

import com.ohgiraffers.geogieoddae.restaurant.command.dto.RestaurantDto;

public interface RestaurantService {
    //가게 등록
    void registerRestaurant(RestaurantDto restaurantDto);
    //가게 삭제
    void deleteRestaurant(Long RestaurantId);
    //가게 수정
//    void updateRestaurant(Long RestaurantId, RestaurantDto restaurantDto);

}
