package com.ohgiraffers.geogieoddae.sports.command.repository;

import com.ohgiraffers.geogieoddae.sports.command.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, Long> {
}
