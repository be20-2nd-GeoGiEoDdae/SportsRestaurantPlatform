package com.geogieoddae.mainservice.restaurant.command.repository.restaurant;

import com.geogieoddae.mainservice.restaurant.command.entity.restaurant.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

}
