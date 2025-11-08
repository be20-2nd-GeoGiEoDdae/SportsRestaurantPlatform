package com.geogieoddae.mainservice.restaurant.command.service.impl;

import com.geogieoddae.mainservice.restaurant.command.dto.RestaurantDto;
import com.geogieoddae.mainservice.restaurant.command.entity.keyword.KeywordEntity;
import com.geogieoddae.mainservice.restaurant.command.entity.keyword.RestaurantKeywordEntity;
import com.geogieoddae.mainservice.restaurant.command.entity.restaurant.RestaurantEntity;
import com.geogieoddae.mainservice.restaurant.command.entity.restaurant.RestaurantPictureEntity;
import com.geogieoddae.mainservice.restaurant.command.repository.keyword.KeywordRepository;
import com.geogieoddae.mainservice.restaurant.command.repository.keyword.RestaurantKeywordRepository;
import com.geogieoddae.mainservice.restaurant.command.repository.restaurant.RestaurantPictureRepository;
import com.geogieoddae.mainservice.restaurant.command.repository.restaurant.RestaurantRepository;
import com.geogieoddae.mainservice.restaurant.command.service.RestaurantService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.geogieoddae.mainservice.restaurant.command.entity.restaurant.RestaurantEntity.updatedRestaurant;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final KeywordRepository keywordRepository;
    private final RestaurantKeywordRepository restaurantKeywordRepository;
    private final RestaurantPictureRepository restaurantPictureRepository;
    @Transactional
    @Override
    public void registerRestaurant(RestaurantDto restaurantDto, List<MultipartFile> pictures) throws IOException {
        RestaurantEntity restaurant = RestaurantEntity.builder()
                .restaurantName(restaurantDto.getRestaurantName())
                .restaurantLocation(restaurantDto.getRestaurantLocation())
                .restaurantCategory(restaurantDto.getRestaurantCategory())
                .restaurantPeopleNumber(restaurantDto.getRestaurantPeopleNumber())
//                .entrepreneur(entrepreneurRepository.findById(restaurantDto.getEntrepreneurId()).orElseThrow())
                .restaurantContents(restaurantDto.getRestaurantContents())
                .restaurantScore(restaurantDto.getRestaurantScore())
                .restaurantIsDeleted(false)
                .build();

        restaurantRepository.save(restaurant);
        // 사진 업로드 처리
        if (pictures != null && !pictures.isEmpty()) {
            List<RestaurantPictureEntity> pictureEntities = new ArrayList<>();

            for (MultipartFile file : pictures) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                String uploadDir = System.getProperty("user.dir") + "/uploads/";
                File dest = new File(uploadDir + fileName);
                dest.getParentFile().mkdirs();
                file.transferTo(dest);

                String fileUrl = "/images/" + fileName; // ← 정적 경로 매핑 (아래 설명)
                pictureEntities.add(RestaurantPictureEntity.builder()
                        .restaurant(restaurant)
                        .restaurantPictureUrl(fileUrl)
                        .build());
            }

            restaurantPictureRepository.saveAll(pictureEntities);
            restaurant.setPictures(pictureEntities);

            if (restaurantDto.getKeywordIds() != null && !restaurantDto.getKeywordIds().isEmpty()) {
                List<KeywordEntity> selectedKeywords = keywordRepository.findAllById(restaurantDto.getKeywordIds());

                if (restaurant.getKeywords() == null) {
                    restaurant.setKeywords(new ArrayList<>());
                }

                for (KeywordEntity keyword : selectedKeywords) {
                    restaurant.getKeywords().add(new RestaurantKeywordEntity(restaurant, keyword));
                }
            }

        }

    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        restaurantRepository.deleteById(restaurantId);
    }

    @Transactional
    @Override
    public void updateRestaurant(Long restaurantId, RestaurantDto restaurantDto, List<MultipartFile> pictures) throws IOException {


        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("식당이 존재하지 않습니다. ID: " + restaurantId));


        updatedRestaurant(restaurantDto, restaurant);


        if (restaurant.getKeywords() != null) {
            restaurant.getKeywords().clear();
        } else {
            restaurant.setKeywords(new ArrayList<>());
        }

        if (restaurantDto.getKeywordIds() != null && !restaurantDto.getKeywordIds().isEmpty()) {
            List<KeywordEntity> keywords = keywordRepository.findAllById(restaurantDto.getKeywordIds());

            for (KeywordEntity keyword : keywords) {
                restaurant.getKeywords().add(new RestaurantKeywordEntity(restaurant, keyword));
            }
        }

        // 기존 사진 삭제 (파일 + DB)
        if (restaurant.getPictures() != null && !restaurant.getPictures().isEmpty()) {
            for (RestaurantPictureEntity pic : restaurant.getPictures()) {
                String filePath = System.getProperty("user.dir") + "/uploads/"
                        + pic.getRestaurantPictureUrl().replace("/images/", "");
                File file = new File(filePath);
                if (file.exists()) file.delete();
            }

            restaurant.getPictures().clear();
        } else {
            restaurant.setPictures(new ArrayList<>());
        }

        // 새 사진 업로드
        if (pictures != null && !pictures.isEmpty()) {
            List<RestaurantPictureEntity> newPictureEntities = new ArrayList<>();

            for (MultipartFile file : pictures) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                String uploadDir = System.getProperty("user.dir") + "/uploads/";
                File dest = new File(uploadDir + fileName);
                dest.getParentFile().mkdirs();
                file.transferTo(dest);

                String fileUrl = "/images/" + fileName;

                newPictureEntities.add(RestaurantPictureEntity.builder()
                        .restaurant(restaurant)
                        .restaurantPictureUrl(fileUrl)
                        .build());
            }


            restaurant.getPictures().addAll(newPictureEntities);


            List<String> pictureUrls = newPictureEntities.stream()
                    .map(RestaurantPictureEntity::getRestaurantPictureUrl)
                    .collect(Collectors.toList());
            restaurantDto.setPictures(pictureUrls);
        }


        restaurantRepository.save(restaurant);
    }


}
