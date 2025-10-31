package com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant;

import com.ohgiraffers.geogieoddae.auth.command.entity.entrepreneur.EntrepreneurEntity;
import com.ohgiraffers.geogieoddae.global.common.entity.BaseTimeEntity;
import com.ohgiraffers.geogieoddae.report.command.entity.blacklist.RestaurantBlacklistEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.KeywordDto;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.PictureDto;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.RestaurantDto;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword.KeywordEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword.RestaurantKeywordEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_code")
    private Long restaurantCode;

    @Column(name = "restaurant_name", nullable = false)
    private String restaurantName;

    @Column(name = "restaurant_location", nullable = false)
    private String restaurantLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "restaurant_category", nullable = false)
    private RestaurantCategory restaurantCategory;

    @Column(name = "restaurant_people_number")
    private Integer restaurantPeopleNumber;

    @Column(name = "restaurant_contents", length = 512)
    private String restaurantContents;

    @Column(name = "restaurant_is_deleted", nullable = false)
    private Boolean restaurantIsDeleted;

    @Column(name = "restaurant_score")
    private Integer restaurantScore;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "entrepreneur_code", nullable = false)
//    private EntrepreneurEntity entrepreneur;

//    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<RestaurantPictureEntity> pictures;

//    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<RestaurantKeywordEntity> keywords;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<RestaurantBlacklistEntity> blacklists;

//    public static void updatedRestaurant(RestaurantDto dto, RestaurantEntity restaurant) {
//        restaurant.setRestaurantName(dto.getRestaurantName());
//        restaurant.setRestaurantLocation(dto.getRestaurantLocation());
//        restaurant.setRestaurantCategory(dto.getRestaurantCategory());
//        restaurant.setRestaurantPeopleNumber(dto.getRestaurantPeopleNumber());
//        restaurant.setRestaurantContents(dto.getRestaurantContents());
//        restaurant.setRestaurantScore(dto.getRestaurantScore());
//    }

    }


