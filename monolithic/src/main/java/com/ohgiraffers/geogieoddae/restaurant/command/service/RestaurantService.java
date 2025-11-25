package com.ohgiraffers.geogieoddae.restaurant.command.service;


import com.ohgiraffers.geogieoddae.restaurant.command.dto.RestaurantCreateDto;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.RestaurantDistanceResponse;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.RestaurantDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface RestaurantService {
    //가게 등록
    void registerRestaurant(RestaurantCreateDto restaurantDto, List<MultipartFile> pictures) throws IOException;
    //가게 삭제
    void deleteRestaurant(Long RestaurantId);
    //가게 수정
    void updateRestaurant(Long RestaurantId, RestaurantCreateDto restaurantDto,List<MultipartFile> pictures) throws IOException;
    List<RestaurantDistanceResponse> getRestaurantsSortedByDistance(double userLat, double userLon);
}
