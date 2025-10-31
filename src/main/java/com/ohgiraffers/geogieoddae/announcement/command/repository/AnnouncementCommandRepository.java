package com.ohgiraffers.geogieoddae.announcement.command.repository;


import com.ohgiraffers.geogieoddae.announcement.command.entity.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementCommandRepository extends JpaRepository<AnnouncementEntity, Long> {}

