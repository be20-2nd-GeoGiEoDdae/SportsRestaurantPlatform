package com.ohgiraffers.geogieoddae.sports.command.repository;

import com.ohgiraffers.geogieoddae.sports.command.entity.SportsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportsRepository extends JpaRepository<SportsEntity,Long> {
}
