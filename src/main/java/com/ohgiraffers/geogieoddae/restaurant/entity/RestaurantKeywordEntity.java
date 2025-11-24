package com.ohgiraffers.geogieoddae.restaurant.entity;

import com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword.KeywordEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword.RestaurantKeywordKey;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "restaurant_keyword")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantKeywordEntity {

    @EmbeddedId
    private RestaurantKeywordKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("restaurantCode")
    @JoinColumn(name = "restaurant_code")
    private RestaurantEntity restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("keywordCode")
    @JoinColumn(name = "keyword_code")
    private KeywordEntity keyword;
}
