package com.geogieoddae.mainservice.sports.command.repository;

import com.geogieoddae.mainservice.sports.command.entity.SportsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportsRepository extends JpaRepository<SportsEntity,Long> {
}
