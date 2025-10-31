package com.ohgiraffers.geogieoddae.restaurant.command.repository;

import com.ohgiraffers.geogieoddae.restaurant.command.entity.keyword.RestaurantKeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantKeywordRepository extends JpaRepository<RestaurantKeywordEntity,Long> {
}
