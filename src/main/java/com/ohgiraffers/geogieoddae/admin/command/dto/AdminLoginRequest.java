package com.ohgiraffers.geogieoddae.admin.command.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminLoginRequest {
    private String adminId;
    private String adminPassword;
    // getter, setter
}
