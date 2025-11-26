package com.ohgiraffers.geogieoddae.pay.query.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class BillingQueryFindUserResponse {
  private Integer entrepreneurSubscribePayment;
  private LocalDateTime entrepreneurSubscribeUpdatedAt;
  private LocalDateTime entrepreneurSubscribeEndAt;
  private String entrepreneurSubscribeOrderId;

}
