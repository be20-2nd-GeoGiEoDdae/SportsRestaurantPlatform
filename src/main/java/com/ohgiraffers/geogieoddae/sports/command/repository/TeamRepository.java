package com.ohgiraffers.geogieoddae.sports.command.repository;

import com.ohgiraffers.geogieoddae.sports.command.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity,Long> {
}
