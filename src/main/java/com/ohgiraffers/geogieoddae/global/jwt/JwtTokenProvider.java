/* 토큰 해결사 - 토큰 생성/파싱/유효성 검증/사용자 ID 추출/토큰 분리 메서드 제공
 * @Author : 김성현
 * @Date : 2025-10-31
 * @Version : 1.0
 */

package com.ohgiraffers.geogieoddae.global.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    // 32바이트 이상 안전한 시크릿 키
    private final String secretString = "MySuperSecretKeyForGeogieoddaeProjectIsVeryLongAndSecure12345";
    public final SecretKey secretKey = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));

    private final long accessTokenValidity = 1000 * 60 * 60; // 1시간
    private final long refreshTokenValidity = 1000 * 60 * 60 * 24 * 7; // 7일

    // Refresh Token 만료 시간 반환
    public LocalDateTime getRefreshTokenExpiryAsLocalDateTime() {
        return LocalDateTime.now().plusSeconds(refreshTokenValidity / 1000);
    }

    // Access Token 생성
    public String generateAccessToken(String adminId) {
        return Jwts.builder()
                .setSubject(adminId)
                .claim("role", "ROLE_ADMIN")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // Refresh Token 생성
    public String generateRefreshToken(String adminId) {
        return Jwts.builder()
                .setSubject(adminId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidity))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰 유효성 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.warn("❌ 잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.warn("❌ 만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.warn("❌ 지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.warn("❌ JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    // 토큰에서 Admin ID 추출
    public String getAdminIdFromToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // 요청 헤더에서 토큰 추출
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}