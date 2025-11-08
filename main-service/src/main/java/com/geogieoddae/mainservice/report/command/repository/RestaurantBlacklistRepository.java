package com.geogieoddae.mainservice.report.command.repository;

import com.geogieoddae.mainservice.report.command.entity.blacklist.RestaurantBlacklistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantBlacklistRepository extends JpaRepository<RestaurantBlacklistEntity, Long> {

    boolean existsByRestaurant_RestaurantCode(Long restaurantCode);

    Optional<RestaurantBlacklistEntity> findByRestaurant_RestaurantCode(Long restaurantCode);
}
