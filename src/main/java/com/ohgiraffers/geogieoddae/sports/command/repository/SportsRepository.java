package com.ohgiraffers.geogieoddae.sports.command.repository;

import com.ohgiraffers.geogieoddae.sports.command.entity.SportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportsRepository extends JpaRepository<SportEntity, Long> {
}
