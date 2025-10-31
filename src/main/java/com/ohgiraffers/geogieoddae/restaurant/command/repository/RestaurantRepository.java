package com.ohgiraffers.geogieoddae.restaurant.command.repository;

import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

}
