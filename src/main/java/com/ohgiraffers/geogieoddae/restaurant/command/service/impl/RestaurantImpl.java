package com.ohgiraffers.geogieoddae.restaurant.command.service.impl;

import com.ohgiraffers.geogieoddae.restaurant.command.dto.RestaurantDto;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword.KeywordEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword.RestaurantKeywordEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.keyword.KeywordRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.restaurant.RestaurantKeywordRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.restaurant.RestaurantRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final KeywordRepository keywordRepository;
    private final RestaurantKeywordRepository restaurantKeywordRepository;

    public void registerRestaurant(RestaurantDto request) {


        RestaurantEntity restaurant = RestaurantEntity.builder()
                .restaurantName(request.getRestaurantName())
                .restaurantLocation(request.getRestaurantLocation())
                .restaurantCategory(request.getRestaurantCategory())
                .restaurantPeopleNumber(request.getRestaurantPeopleNumber())
                .restaurantContents(request.getRestaurantContents())
                .restaurantScore(request.getRestaurantScore())
                .restaurantIsDeleted(false)
                .build();

        restaurantRepository.save(restaurant);

        List<KeywordEntity> keywords = keywordRepository.findAllById(request.getKeywordIds());
        List<RestaurantKeywordEntity> mappings = keywords.stream()
                .map(keyword -> new RestaurantKeywordEntity(restaurant, keyword))
                .toList();

        restaurantKeywordRepository.saveAll(mappings);
    }
    @Override
    public void deleteRestaurant(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }

//    @Transactional
//    @Override
//    public void updateRestaurant(Long restaurantId, RestaurantDto restaurantDto) {
//        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 가게를 찾을 수 없습니다. id=" + restaurantId));
//
//        restaurant.updateInfo(restaurantDto);
//
//        if (restaurantDto.getPicture() != null) {
//            restaurant.updatePictures(restaurantDto.getPicture());
//        }
//
//        if (restaurantDto.getKeyword() != null) {
//            restaurant.updateKeywords(restaurantDto.getKeyword());
//        }
//
//        restaurantRepository.save(restaurant);
//    }
}
