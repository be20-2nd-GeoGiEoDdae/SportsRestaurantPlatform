package com.ohgiraffers.geogieoddae.admin.command.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AdminRefreshTokenRequest {
    private final String adminRefreshToken;
}
