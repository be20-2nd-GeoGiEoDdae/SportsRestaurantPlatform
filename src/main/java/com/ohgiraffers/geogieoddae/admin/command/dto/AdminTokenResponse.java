package com.ohgiraffers.geogieoddae.admin.command.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminTokenResponse {
    private String accessToken;
    private String refreshToken;
}