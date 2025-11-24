package com.ohgiraffers.geogieoddae.auth.command.controller;

import com.ohgiraffers.geogieoddae.global.jwt.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final JwtTokenProvider jwtTokenProvider;

    public TestController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/token")
    public ResponseEntity<Map<String, String>> generateTestToken() {
        // 테스트용 JWT 토큰 생성
        String accessToken = jwtTokenProvider.generateUserAccessTokenWithSocial(
                "1", // 사용자 ID
                "ROLE_USER", // 역할
                "test@example.com", // 이메일
                "12345", // 소셜 ID
                "KAKAO" // 소셜 타입
        );
        
        String refreshToken = jwtTokenProvider.generateUserRefreshToken("1");
        
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        tokens.put("message", "테스트용 JWT 토큰이 생성되었습니다.");
        
        return ResponseEntity.ok(tokens);
    }
}
