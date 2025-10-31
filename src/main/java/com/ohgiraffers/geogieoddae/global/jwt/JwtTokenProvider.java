/* 토큰 해결사 - 토큰 생성/파싱/유효성 검증/사용자 ID 추출/토큰 분리 메서드 제공
* @Author : 김성현
* @Date : 2025-10-31
* @Version : 1.0
* @*/

package com.ohgiraffers.geogieoddae.global.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Slf4j
@Component
public class JwtTokenProvider {

    // 32바이트 이상의 안전한 시크릿 키로 변경
    private final String secretString = "MySuperSecretKeyForGeogieoddaeProjectIsVeryLongAndSecure12345";
    SecretKey secretKey = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
    private final long accessTokenValidity = 1000 * 60 * 60; // 1시간
    private final long refreshTokenValidity = 1000 * 60 * 60 * 24 * 7; // 7일
    /*
    @PostConstruct
    protected void init() {
        // 문자열 키를 이용해 안전한 Key 객체 생성
        secretKey = Keys.hmacShaKeyFor(secretString.getBytes());
    }
    */
    // LocalDateTime 반환 메서드 추가
    public LocalDateTime getRefreshTokenExpiryAsLocalDateTime() {
        return LocalDateTime.now().plusSeconds(refreshTokenValidity / 1000);
    }

    // 관리자 에 대해 갱신토큰 만들어주기
    public String generateRefreshToken(String adminId) {
        return Jwts.builder()
                .setSubject(adminId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidity))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // 관리자 에 대해 생성토큰 만들어주기
    public String generateAccessToken(String adminId) {
        return Jwts.builder()
                .setSubject(adminId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // 토큰에서 사용자 ID 추출
    public String getAdminIdFromToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // Request Header에서 토큰 값 가져오기
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | io.jsonwebtoken.MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (io.jsonwebtoken.UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}
