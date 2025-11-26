package com.ohgiraffers.geogieoddae.viewing.query.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ViewingUserResponseDto {
  private Boolean viewingUserIsAttend;
  private LocalDateTime viewingAt;
  private String viewingTitle;
  private Integer viewingUserDeposit;
  private String restaurantLocation;
  private Long viewingCode;
  private Long restaurantCode;


}
