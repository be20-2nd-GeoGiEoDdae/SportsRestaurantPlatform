package com.geogieoddae.mainservice.notification.command.repository;

import com.geogieoddae.mainservice.notification.command.entity.NotificationEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity,Long> {

    List<NotificationEntity> findByMemberCode(Long memberCode);
}
