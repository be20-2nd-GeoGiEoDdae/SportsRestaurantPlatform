package com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "restaurant_picture")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantPictureEntity {

    @Id
    @Column(name = "restaurant_picture_code")
    private Long restaurantPictureCode;

    @Column(name = "restaurant_picture_url", nullable = false, length = 512)
    private String restaurantPictureUrl;

    @ManyToOne
    @JoinColumn(name = "restaurant_code", nullable = false)
    private RestaurantEntity restaurant;
}
