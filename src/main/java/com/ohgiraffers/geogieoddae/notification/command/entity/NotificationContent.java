package com.ohgiraffers.geogieoddae.notification.command.entity;

import lombok.Getter;

@Getter
public enum NotificationContent {
  viewingConfirm(1),
  viewingCancel(2),
  bookMarkRegister(3),
  entrepreneurRestaurantRegister(4),
  restaurantReviewRegister(5),
  restaurantReportRegister(6),
  restaurantBlackListRegister(7),
  subscribeExpire(8);
  private final int value;
  NotificationContent(int value){
    this.value=value;
  }
  public static int get(String n){return valueOf(n).value;}

}
