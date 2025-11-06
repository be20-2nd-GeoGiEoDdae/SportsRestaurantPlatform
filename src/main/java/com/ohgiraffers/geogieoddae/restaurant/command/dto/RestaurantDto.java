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
public class RestaurantDto {
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
    private Long entrepreneurId;
    @NotBlank
    private List<Long> keywordIds;
    @NotBlank
    private List<String> pictures;
}
