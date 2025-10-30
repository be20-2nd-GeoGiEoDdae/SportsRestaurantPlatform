package com.ohgiraffers.geogieoddae.admin.command.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AdminLoginRequest {
    private final String adminId;
    private final String adminPassword;
    // getter, setter
}
