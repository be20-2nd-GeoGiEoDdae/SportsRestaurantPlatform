package com.ohgiraffers.geogieoddae.restaurant.command.service.impl;

import com.ohgiraffers.geogieoddae.restaurant.command.dto.RestaurantDto;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword.KeywordEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword.RestaurantKeywordEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantPictureEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.keyword.KeywordRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.keyword.RestaurantKeywordRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.restaurant.RestaurantPictureRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.restaurant.RestaurantRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.review.ReviewRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.service.RestaurantService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantEntity.updatedRestaurant;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final KeywordRepository keywordRepository;
    private final RestaurantKeywordRepository restaurantKeywordRepository;
    private final ReviewRepository reviewRepository;
    private final RestaurantPictureRepository restaurantPictureRepository;

    @Transactional
    @Override
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
        if (request.getPictures() != null && !request.getPictures().isEmpty()) {
            List<RestaurantPictureEntity> pictures = request.getPictures().stream()
                    .map(picDto -> RestaurantPictureEntity.builder()
                            .restaurant(restaurant)
                            .restaurantPictureUrl(picDto.getPictureUrl())
                            .build())
                    .collect(Collectors.toList());

            restaurantPictureRepository.saveAll(pictures);
            restaurant.setPictures(pictures);
        }
    }
    @Override
    public void deleteRestaurant(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }

    @Transactional
    @Override
    public void updateRestaurant(Long restaurantId, RestaurantDto dto) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("식당이 존재하지 않습니다. ID: " + restaurantId));

        updatedRestaurant(dto, restaurant);

        restaurantKeywordRepository.deleteAll(restaurant.getKeywords());
        restaurant.getKeywords().clear();

        List<KeywordEntity> keywords = keywordRepository.findAllById(dto.getKeywordIds());
        List<RestaurantKeywordEntity> newMappings = new ArrayList<>();

        for (KeywordEntity keyword : keywords) {
            boolean exists = restaurant.getKeywords().stream()
                    .anyMatch(k -> k.getKeyword().getKeywordCode().equals(keyword.getKeywordCode()));

            if (!exists) {
                RestaurantKeywordEntity mapping = new RestaurantKeywordEntity(restaurant, keyword);
                newMappings.add(mapping);
                restaurant.getKeywords().add(mapping);
            }
        }

    }


}
