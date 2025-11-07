package com.ohgiraffers.geogieoddae.restaurant.command.service.impl;

import com.ohgiraffers.geogieoddae.global.apikey.GoogleGeocodingService;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.RestaurantDistanceResponse;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.RestaurantDto;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword.KeywordEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword.RestaurantKeywordEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantPictureEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.keyword.KeywordRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.keyword.RestaurantKeywordRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.restaurant.RestaurantPictureRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.repository.restaurant.RestaurantRepository;
import com.ohgiraffers.geogieoddae.restaurant.command.service.RestaurantService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    private final RestaurantPictureRepository restaurantPictureRepository;
    private final GoogleGeocodingService googleGeocodingService;


    public List<RestaurantDistanceResponse> getRestaurantsSortedByDistance(double userLat, double userLon) {

        List<Object[]> results = restaurantRepository.findAllOrderByDistance(userLat, userLon);

        return results.stream().map(obj -> {
            Long code = ((Number) obj[0]).longValue();
            String name = (String) obj[1];
            String location = (String) obj[2];
            Double lat = (Double) obj[3];
            Double lon = (Double) obj[4];
            Double distanceKm = (Double) obj[5];

            // ✅ 거리 단위 변환
            String formattedDistance = formatDistance(distanceKm);

            return new RestaurantDistanceResponse(code, name, location, lat, lon, formattedDistance);
        }).collect(Collectors.toList());
    }

    /** ✅ 1km 미만이면 m단위, 이상이면 km단위로 표시 */
    private String formatDistance(double distanceKm) {
        if (distanceKm < 1.0) {
            // 1km 미만 → m 단위로 (소수점 1자리까지)
            double meters = distanceKm * 1000;
            return String.format("%.1fm", meters);
        } else {
            // 1km 이상 → km 단위로 (소수점 1자리까지)
            return String.format("%.1fkm", distanceKm);
        }
    }

    @Transactional
    @Override
    public void registerRestaurant(RestaurantDto restaurantDto, List<MultipartFile> pictures) throws IOException {

        // ✅ 주소 → 좌표 변환
        double[] coordinates = googleGeocodingService.getCoordinates(restaurantDto.getRestaurantLocation());
        double latitude = coordinates[0];
        double longitude = coordinates[1];

        // ✅ 엔티티 생성
        RestaurantEntity restaurant = RestaurantEntity.builder()
                .restaurantName(restaurantDto.getRestaurantName())
                .restaurantLocation(restaurantDto.getRestaurantLocation())
                .latitude(latitude)
                .longitude(longitude)
                .restaurantCategory(restaurantDto.getRestaurantCategory())
                .restaurantPeopleNumber(restaurantDto.getRestaurantPeopleNumber())
                .restaurantContents(restaurantDto.getRestaurantContents())
                .restaurantScore(restaurantDto.getRestaurantScore())
                .restaurantIsDeleted(false)
                .build();

        restaurantRepository.save(restaurant);

        // ✅ 사진 처리 (저장 후 DTO에 바로 반영)
        if (pictures != null && !pictures.isEmpty()) {
            List<RestaurantPictureEntity> pictureEntities = new ArrayList<>();

            for (MultipartFile file : pictures) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                String uploadDir = System.getProperty("user.dir") + "/uploads/";
                File dest = new File(uploadDir + fileName);
                dest.getParentFile().mkdirs();
                file.transferTo(dest);

                String fileUrl = "/images/" + fileName;

                pictureEntities.add(RestaurantPictureEntity.builder()
                        .restaurant(restaurant)
                        .restaurantPictureUrl(fileUrl)
                        .build());
            }

            restaurantPictureRepository.saveAll(pictureEntities);

            // ✅ LAZY 초기화 없이 바로 DTO에 URL 세팅
            List<String> pictureUrls = pictureEntities.stream()
                    .map(RestaurantPictureEntity::getRestaurantPictureUrl)
                    .collect(Collectors.toList());
            restaurantDto.setPictures(pictureUrls);

            // 양방향 관계 유지 (DB 동기화 목적)
            restaurant.setPictures(pictureEntities);
        }

        // ✅ 키워드 연결
        if (restaurantDto.getKeywordIds() != null && !restaurantDto.getKeywordIds().isEmpty()) {
            List<KeywordEntity> selectedKeywords = keywordRepository.findAllById(restaurantDto.getKeywordIds());
            if (restaurant.getKeywords() == null) restaurant.setKeywords(new ArrayList<>());
            for (KeywordEntity keyword : selectedKeywords) {
                restaurant.getKeywords().add(new RestaurantKeywordEntity(restaurant, keyword));
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
