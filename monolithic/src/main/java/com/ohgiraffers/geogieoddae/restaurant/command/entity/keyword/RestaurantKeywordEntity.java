package com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword;

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
    private RestaurantKeywordKey id  = new RestaurantKeywordKey();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("restaurantCode")
    @JoinColumn(name = "restaurant_code", nullable = false)
    private RestaurantEntity restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("keywordCode") // RestaurantKeywordKey.keywordCode 와 연결
    @JoinColumn(name = "keyword_code", nullable = false)
    private KeywordEntity keyword;

    public RestaurantKeywordEntity(RestaurantEntity restaurant, KeywordEntity keyword) {
        this.restaurant = restaurant;
        this.keyword = keyword;
        this.id = new RestaurantKeywordKey(
                restaurant.getRestaurantCode(),
                keyword.getKeywordCode()
        );
    }
}
