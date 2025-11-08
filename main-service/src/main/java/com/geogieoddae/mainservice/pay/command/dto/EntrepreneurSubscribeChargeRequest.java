package com.geogieoddae.mainservice.pay.command.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntrepreneurSubscribeChargeRequest {
  private Long amount;
  private String customerKey;
  private String orderId;
  private String orderName;
  private String customerEmail;
  private String customerName;

}
