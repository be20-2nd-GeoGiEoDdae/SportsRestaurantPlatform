package com.ohgiraffers.geogieoddae.announcement.query.repository;

import com.ohgiraffers.geogieoddae.announcement.command.entity.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementQueryRepository extends JpaRepository<AnnouncementEntity, Long> {

}
