package com.ohgiraffers.geogieoddae.notification.query.dto;

import com.ohgiraffers.geogieoddae.notification.command.entity.NotificationStatus;
import lombok.Getter;

@Getter
public class NotificationTypeQueryResponse {

  private Long notificationTypeCode;

  private String notificationTypeType;

  private NotificationStatus notificationTypeStatus;

  private String notificationContent;
}
