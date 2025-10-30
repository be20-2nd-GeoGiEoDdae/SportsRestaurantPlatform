package com.ohgiraffers.geogieoddae.admin.command.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AdminTokenRequest {
    private final String refreshToken;
}
