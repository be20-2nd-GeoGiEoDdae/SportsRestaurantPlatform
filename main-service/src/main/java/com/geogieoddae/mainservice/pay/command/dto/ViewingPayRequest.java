package com.geogieoddae.mainservice.pay.command.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewingPayRequest {
  private Long viewingPayPrice;
  private Long userCode;
  private Long viewingCode;
}
