package com.ohgiraffers.geogieoddae.report.command.repository;

import com.ohgiraffers.geogieoddae.report.command.entity.blacklist.RestaurantBlacklistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantBlacklistRepository extends JpaRepository<RestaurantBlacklistEntity, Long> {
}
