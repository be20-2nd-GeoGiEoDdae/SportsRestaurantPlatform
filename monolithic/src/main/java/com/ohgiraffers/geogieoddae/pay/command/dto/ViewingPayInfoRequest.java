package com.ohgiraffers.geogieoddae.pay.command.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewingPayInfoRequest {
  private Long viewingPayCode;
  private Long viewingCode;
  private Integer viewingPayPrice;
}
