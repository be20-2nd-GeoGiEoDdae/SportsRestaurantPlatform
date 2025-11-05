package com.ohgiraffers.geogieoddae.auth.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SocialLoginResponseDto {
    private String accessToken;
    private String refreshToken;
    private String userName;
    private String userEmail;
}
