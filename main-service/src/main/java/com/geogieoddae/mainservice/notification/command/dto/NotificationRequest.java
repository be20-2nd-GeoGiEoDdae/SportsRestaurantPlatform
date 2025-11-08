package com.geogieoddae.mainservice.notification.command.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequest {
  /*private Boolean notificationChecked;*/
  private Long memberCode;
  private Long notificationTypeCode;
}
