package com.ohgiraffers.geogieoddae.auth.command.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ohgiraffers.geogieoddae.auth.command.dto.KakaoUserInfoDto;
import com.ohgiraffers.geogieoddae.auth.command.dto.SocialLoginResponseDto;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.auth.command.service.SocialLoginService;
import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Tag(name = "소셜 로그인 api")
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
