package com.ohgiraffers.geogieoddae.pay.query.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class BillingQueryDto {

  private String userName;
  private LocalDateTime entrepreneurSubscribeUpdatedAt;
  private LocalDateTime entrepreneurSubscribeEndAt;
  private Integer entrepreneurSubscribePayment;
  private String orderId;


}
