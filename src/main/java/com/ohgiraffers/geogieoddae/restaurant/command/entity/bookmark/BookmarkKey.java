package com.ohgiraffers.geogieoddae.restaurant.command.entity.bookmark;

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
public class BookmarkKey implements Serializable {
    @Column(name = "user_code")
    private Long userCode;

    @Column(name = "restaurant_code")
    private Long restaurantCode;
}
