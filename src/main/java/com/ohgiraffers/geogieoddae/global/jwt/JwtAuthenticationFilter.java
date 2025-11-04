/* JWT 인증 필터 - 요청 시 JWT 유효성 검사 및 인증객체 등록
 * @Author : 김성현
 * @Date : 2025-10-31
 * @Version : 1.0
 */

package com.ohgiraffers.geogieoddae.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = jwtTokenProvider.resolveToken(request);

        // 토큰 로그
        System.out.println("token : " + token + "");
        System.out.println("jwtTokenProvider.validateToken(token) : " + jwtTokenProvider.validateToken(token) + "");

        if (token != null && jwtTokenProvider.validateToken(token)) {
            try {
                // 토큰 파싱
                Claims claims = Jwts.parser()
                        .verifyWith(jwtTokenProvider.secretKey)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();

                // claim 로그
                System.out.println("claims : " + claims + " ");

                String adminId = claims.getSubject();
                String role = claims.get("role", String.class);

                // adminId & role 로그
                System.out.println("adminId : " + adminId + " ");
                System.out.println("role : " + role + " ");

                // 인증객체 생성 및 SecurityContext에 등록
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        adminId, null, Collections.singletonList(new SimpleGrantedAuthority(role))
                );

                // 인증객체 로그
                System.out.println("authentication : " + authentication + " ");

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                logger.error("JWT 필터 처리 중 오류 발생");
            }
        }

        filterChain.doFilter(request, response);
    }
}
