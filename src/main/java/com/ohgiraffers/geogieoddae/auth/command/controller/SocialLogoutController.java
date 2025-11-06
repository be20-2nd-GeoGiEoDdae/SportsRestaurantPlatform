package com.ohgiraffers.geogieoddae.auth.command.controller;

import com.ohgiraffers.geogieoddae.auth.command.service.SocialLogoutService;
import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InaccessibleObjectException;

@RestController
@RequestMapping("/api/auth")
public class SocialLogoutController {

    private final SocialLogoutService socialLogoutService;

    public SocialLogoutController(SocialLogoutService socialLogoutService) {
        this.socialLogoutService = socialLogoutService;
    }

    @PostMapping("/social-logout")
    public ResponseEntity<ApiResponse> socialLogout(@RequestHeader("Authorization") String accessToken) {
        socialLogoutService.logoutKakao(accessToken);
        // JWT/세션 삭제는 프론트에서 처리(백엔드에서 토큰 블랙리스트 관리 가능)
        return ResponseEntity.ok(ApiResponse.success("로그아웃 완료"));
    }
}
