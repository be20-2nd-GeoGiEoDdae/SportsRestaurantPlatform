package com.geogieoddae.mainservice.restaurant.command.repository.keyword;

import com.geogieoddae.mainservice.restaurant.command.entity.keyword.RestaurantKeywordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantKeywordRepository extends JpaRepository<RestaurantKeywordEntity,Long> {
}
