package com.geogieoddae.mainservice.restaurant.command.repository.restaurant;

import com.geogieoddae.mainservice.restaurant.command.entity.restaurant.RestaurantPictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantPictureRepository extends JpaRepository<RestaurantPictureEntity,Long> {
}
