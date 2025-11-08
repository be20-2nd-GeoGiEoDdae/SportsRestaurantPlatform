package com.geogieoddae.mainservice.notification.command.repository;

import com.geogieoddae.mainservice.notification.command.entity.NotificationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationTypeRepository extends JpaRepository<NotificationTypeEntity,Long> {

}
