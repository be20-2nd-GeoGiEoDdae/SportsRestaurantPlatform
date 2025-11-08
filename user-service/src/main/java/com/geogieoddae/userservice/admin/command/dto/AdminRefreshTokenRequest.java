package com.geogieoddae.userservice.admin.command.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AdminRefreshTokenRequest {
    private final String adminRefreshToken;
}
