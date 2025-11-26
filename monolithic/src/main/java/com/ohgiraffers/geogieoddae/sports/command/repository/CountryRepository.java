package com.ohgiraffers.geogieoddae.sports.command.repository;

import com.ohgiraffers.geogieoddae.sports.command.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
    List<CountryEntity> findBySport_SportCode(Long sportCode);
}

