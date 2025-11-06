package com.ohgiraffers.geogieoddae.pay.command.dto;
import com.ohgiraffers.geogieoddae.pay.command.entity.ViewingPayStatus;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewingPayInfoResponse {
  private Long viewingCode;
  private Long viewingPayPrice;
  private LocalDateTime viewingPayRefundDate;
  private String customerKey;
  private String orderId;
  private String paymentKey;
  private ViewingPayStatus viewingPayStatus;

  public ViewingPayInfoResponse(Long viewingPayCode, Long viewingPayPrice,String orderId) {
    this.viewingCode = viewingPayCode;
    this.viewingPayPrice = viewingPayPrice;
    this.orderId = orderId;
  }
}
