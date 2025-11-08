package com.geogieoddae.mainservice.restaurant.command.entity.bookmark;

import com.geogieoddae.mainservice.restaurant.command.entity.restaurant.RestaurantEntity;
import jakarta.persistence.*;
import lombok.*;

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

    private Long memberCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("restaurantCode")
    @JoinColumn(name = "restaurant_code")
    private RestaurantEntity restaurant;



}
