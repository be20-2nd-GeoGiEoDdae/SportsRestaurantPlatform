package com.geogieoddae.mainservice.announcement.query.repository;



import com.geogieoddae.mainservice.announcement.command.entity.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementQueryRepository extends JpaRepository<AnnouncementEntity, Long> {

}
