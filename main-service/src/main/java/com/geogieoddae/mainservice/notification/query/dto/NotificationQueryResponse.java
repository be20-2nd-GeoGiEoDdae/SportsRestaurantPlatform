package com.geogieoddae.mainservice.notification.query.dto;

import lombok.Getter;

@Getter
public class NotificationQueryResponse {

  private Long notificationCode;

  private Boolean notificationChecked;

  private String  notificationTypeType;

  private String notificationContent;
}
