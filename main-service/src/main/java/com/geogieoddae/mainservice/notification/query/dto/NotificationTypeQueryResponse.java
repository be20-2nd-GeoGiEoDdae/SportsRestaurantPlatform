package com.geogieoddae.mainservice.notification.query.dto;

import com.geogieoddae.mainservice.notification.command.entity.NotificationStatus;
import lombok.Getter;

@Getter
public class NotificationTypeQueryResponse {

  private Long notificationTypeCode;

  private String notificationTypeType;

  private NotificationStatus notificationTypeStatus;

  private String notificationContent;
}
