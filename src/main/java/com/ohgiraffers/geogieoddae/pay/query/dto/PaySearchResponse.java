package com.ohgiraffers.geogieoddae.pay.query.dto;

import com.ohgiraffers.geogieoddae.pay.command.entity.ViewingPayStatus;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaySearchResponse {
  private Long viewingPayCode;
  private Integer viewingPayPrice;
  private LocalDateTime createdAt;
  private LocalDateTime viewingPayRefundDate;
  private String userName;
  private String userEmail;
  private String customerKey;
  private String orderId;
  private String paymentKey;
  private ViewingPayStatus viewingPayStatus;

}
