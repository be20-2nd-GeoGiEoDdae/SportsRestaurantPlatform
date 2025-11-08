package com.geogieoddae.mainservice.report.command.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantBlacklistRequestDto {
    private Long reportCode;
    private Long restaurantCode;
}
