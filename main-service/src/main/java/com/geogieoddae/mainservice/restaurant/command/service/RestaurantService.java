package com.geogieoddae.mainservice.restaurant.command.service;


import com.geogieoddae.mainservice.restaurant.command.dto.RestaurantDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface RestaurantService {
    //가게 등록
    void registerRestaurant(RestaurantDto restaurantDto, List<MultipartFile> pictures) throws IOException;
    //가게 삭제
    void deleteRestaurant(Long RestaurantId);
    //가게 수정
    void updateRestaurant(Long RestaurantId, RestaurantDto restaurantDto,List<MultipartFile> pictures) throws IOException;

}
