package com.ohgiraffers.geogieoddae.restaurant.query.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private String createdAt;

    // ⭐ 새로 추가해야 하는 필드
    private String pictureUrls;   // GROUP_CONCAT 결과
    private String keywords;      // GROUP_CONCAT 결과
    private Double distance;      // 거리 계산 값
    private Boolean bookmarked;
    private Double reviewAvg;
}
