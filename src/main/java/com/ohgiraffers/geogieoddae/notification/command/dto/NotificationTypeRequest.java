package com.ohgiraffers.geogieoddae.notification.command.dto;

import com.ohgiraffers.geogieoddae.notification.command.entity.NotificationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationTypeRequest {
  private String notificationTypeType;
  private NotificationStatus notificationTypeStatus;
  private String notificationContent;
}
