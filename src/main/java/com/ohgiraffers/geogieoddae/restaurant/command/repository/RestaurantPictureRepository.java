package com.ohgiraffers.geogieoddae.restaurant.command.repository;

import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantPictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantPictureRepository extends JpaRepository<RestaurantPictureEntity,Long> {
}
