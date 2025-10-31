package com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RestaurantKeywordKey implements Serializable {
    private Long restaurantCode;
    private Long keywordCode;
}
