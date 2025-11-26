package com.ohgiraffers.geogieoddae.report.query.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlacklistQueryDto {
    private String restaurantName;
    private Integer reportCount;   // ✅ 가게별 신고 누적 횟수
}
