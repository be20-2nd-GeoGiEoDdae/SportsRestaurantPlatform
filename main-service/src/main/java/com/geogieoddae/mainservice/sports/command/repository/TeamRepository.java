package com.geogieoddae.mainservice.sports.command.repository;

import com.geogieoddae.mainservice.sports.command.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity,Long> {
}
