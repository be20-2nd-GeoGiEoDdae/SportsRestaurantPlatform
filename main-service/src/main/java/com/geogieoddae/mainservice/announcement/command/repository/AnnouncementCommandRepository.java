package com.geogieoddae.mainservice.announcement.command.repository;

import com.geogieoddae.mainservice.announcement.command.entity.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementCommandRepository extends JpaRepository<AnnouncementEntity, Long> {

}

