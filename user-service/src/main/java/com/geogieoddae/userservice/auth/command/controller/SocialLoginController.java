package com.geogieoddae.userservice.auth.command.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.geogieoddae.userservice.auth.command.dto.KakaoUserInfoDto;
import com.geogieoddae.userservice.auth.command.dto.SocialLoginResponseDto;
import com.geogieoddae.userservice.auth.command.entity.user.UserEntity;
import com.geogieoddae.userservice.auth.command.service.SocialLoginService;
import com.geogieoddae.userservice.global.common.dto.ApiResponse;
import com.geogieoddae.userservice.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SocialLoginController {
    
    private final SocialLoginService socialLoginService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/api/auth/kakao/callback")
    public ResponseEntity<ApiResponse<SocialLoginResponseDto>> kakaoCallback(@RequestBody KakaoUserInfoDto kakaoUserInfo) {
        UserEntity user = socialLoginService.findOrCreateUserByKakao(kakaoUserInfo);
        String accessToken = jwtTokenProvider.generateUserAccessToken(
            user.getUserCode().toString(), 
            user.getUserRole().name()
        );
        String refreshToken = jwtTokenProvider.generateUserRefreshToken(user.getUserCode().toString());
        // 토큰 및 만료시간 계산은 JwtTokenProvider에서 관리
        SocialLoginResponseDto response = new SocialLoginResponseDto(
            accessToken,
            refreshToken,
            user.getUserName(),
            user.getUserEmail()
        );
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
