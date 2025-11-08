package com.geogieoddae.mainservice.notification.command.dto;

import com.geogieoddae.mainservice.notification.command.entity.NotificationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationTypeRequest {
  private String notificationTypeType;
  private NotificationStatus notificationTypeStatus;
  private String notificationContent;
}
