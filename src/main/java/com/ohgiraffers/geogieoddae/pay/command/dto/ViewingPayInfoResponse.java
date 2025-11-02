package com.ohgiraffers.geogieoddae.pay.command.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewingPayInfoResponse {
  private Long viewingCode;
  private Integer viewingPayPrice;
  private String orderId;
  private Integer deposit;

  public ViewingPayInfoResponse(Long viewingPayCode, Integer viewingPayPrice,String orderId,Integer deposit) {
    this.viewingCode = viewingPayCode;
    this.viewingPayPrice = viewingPayPrice;
    this.orderId = orderId;
    this.deposit = deposit;
  }
}
