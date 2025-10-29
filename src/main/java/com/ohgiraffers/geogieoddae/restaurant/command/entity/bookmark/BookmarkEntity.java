package com.ohgiraffers.geogieoddae.restaurant.command.entity.bookmark;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.bookmark.BookmarkKey;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "bookmark")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookmarkEntity {

    @EmbeddedId
    private BookmarkKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userCode")
    @JoinColumn(name = "user_code")
    private UserEntity member;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("restaurantCode")
    @JoinColumn(name = "restaurant_code")
    private RestaurantEntity restaurant;
}
