package com.geogieoddae.mainservice.viewing.command.repository;

import com.geogieoddae.mainservice.viewing.command.entity.ViewingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewingRepository extends JpaRepository<ViewingEntity, Long> {
}
