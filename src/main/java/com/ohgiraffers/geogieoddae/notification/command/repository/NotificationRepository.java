package com.ohgiraffers.geogieoddae.notification.command.repository;

import com.ohgiraffers.geogieoddae.notification.command.entity.NotificationEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity,Long> {

  List<NotificationEntity> findByMember_UserCode(Long userCode );
}
