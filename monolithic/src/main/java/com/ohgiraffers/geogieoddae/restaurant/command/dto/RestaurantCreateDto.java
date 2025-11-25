package com.ohgiraffers.geogieoddae.restaurant.command.dto;

import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class
RestaurantCreateDto {
    @NotBlank
    private String restaurantName;
    @NotBlank
    private String restaurantLocation;
    @NotBlank
    private RestaurantCategory restaurantCategory;
    @NotBlank
    private Integer restaurantPeopleNumber;
    @NotBlank
    private String restaurantContents;
    @NotBlank
    private Integer restaurantScore;
    @NotBlank
    private Long entrepreneurCode;

    private double latitude; // 위도 필드 추가
    private double longitude; // 경도 필드 추가

    private List<Long> keywordIds;

    private List<String> pictures;
}
