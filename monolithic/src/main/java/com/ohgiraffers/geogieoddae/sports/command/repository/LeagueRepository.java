package com.ohgiraffers.geogieoddae.sports.command.repository;

import com.ohgiraffers.geogieoddae.sports.command.entity.LeagueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface LeagueRepository extends JpaRepository<LeagueEntity, Long> {
    List<LeagueEntity> findBySport_SportCodeAndCountry_Id(Long sportCode, Long countryId);
}
