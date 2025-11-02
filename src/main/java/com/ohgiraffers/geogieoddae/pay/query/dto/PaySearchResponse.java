package com.ohgiraffers.geogieoddae.pay.query.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaySearchResponse {
  private Long viewingPayCode;
  private Integer viewingPayPrice;
  private LocalDate createdAt;
  private LocalDate viewingPayRefundDate;
  private String userName;
  private String userEmail;
}
