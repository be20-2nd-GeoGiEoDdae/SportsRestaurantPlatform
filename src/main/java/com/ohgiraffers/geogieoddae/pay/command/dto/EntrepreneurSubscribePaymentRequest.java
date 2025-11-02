package com.ohgiraffers.geogieoddae.pay.command.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntrepreneurSubscribePaymentRequest {
  private Long entrepreneurId   ;
  private Integer entrepreneurSubscribePayment;//구독 결제  금액
  private String entrepreneurSubscribeBillingkey;
}
