// 토큰 생성/파싱/유효성 검증 메서드 제공
package com.ohgiraffers.geogieoddae.global.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    // 32바이트 이상의 안전한 시크릿 키로 변경
    private final String secretString = "MySuperSecretKeyForGeogieoddaeProjectIsVeryLongAndSecure12345";
    private Key secretKey;
    private final long accessTokenValidity = 1000 * 60 * 60; // 1시간
    private final long refreshTokenValidity = 1000 * 60 * 60 * 24 * 7; // 7일

    @PostConstruct
    protected void init() {
        // 문자열 키를 이용해 안전한 Key 객체 생성
        secretKey = Keys.hmacShaKeyFor(secretString.getBytes());
    }

    // 기존 Date 반환 메서드
    public Date getRefreshTokenExpiry() {
        return new Date(System.currentTimeMillis() + refreshTokenValidity);
    }

    // LocalDateTime 반환 메서드 추가
    public LocalDateTime getRefreshTokenExpiryAsLocalDateTime() {
        return LocalDateTime.now().plusSeconds(refreshTokenValidity / 1000);
    }

    public String generateRefreshToken(String adminId) {
        return Jwts.builder()
                .setSubject(adminId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidity))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String generateAccessToken(String adminId) {
        return Jwts.builder()
                .setSubject(adminId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

}
