package com.ohgiraffers.geogieoddae.viewing.command.repository;

import com.ohgiraffers.geogieoddae.viewing.command.entity.ViewingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewingRepository extends JpaRepository<ViewingEntity, Long> {
}
