package com.ohgiraffers.geogieoddae.notification.command.repository;

import com.ohgiraffers.geogieoddae.notification.command.entity.NotificationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationTypeRepository extends JpaRepository<NotificationTypeEntity,Long> {

}
