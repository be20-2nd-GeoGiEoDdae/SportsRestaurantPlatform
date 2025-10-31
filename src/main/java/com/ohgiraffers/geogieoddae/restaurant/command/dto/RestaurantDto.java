package com.ohgiraffers.geogieoddae.restaurant.command.dto;

import com.ohgiraffers.geogieoddae.auth.command.entity.entrepreneur.EntrepreneurEntity;
import com.ohgiraffers.geogieoddae.report.command.entity.blacklist.RestaurantBlacklistEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword.RestaurantKeywordEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantCategory;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantPictureEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {

    private String restaurantName;
    private String restaurantLocation;
    private RestaurantCategory restaurantCategory;
    private Integer restaurantPeopleNumber;
    private String restaurantContents;
    private Integer restaurantScore;
//    private Long entrepreneurId;
    private List<Long> keywordIds;
//    private List<String> pictures;
}
