/* 시스템 문지기 - 모든 HTTP 요청을 가로채는 커스텀 필터
 * @Author : 김성현
 * @Date : 2025-10-31
 * @Version : 1.0
 * @*/

// Spring Security 필터 체인에서 JWT 인증 처리
package com.ohgiraffers.geogieoddae.global.jwt;

import com.ohgiraffers.geogieoddae.admin.command.security.AdminDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final AdminDetailsService adminDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. 요청 헤더에서 토큰 추출 (순수한 토큰 문자열을 가져옴)
        String token = jwtTokenProvider.resolveToken(request);

        // 2. 토큰이 존재하고 유효한지 검사
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 3. 토큰에서 사용자 ID(adminId) 추출
            String adminId = jwtTokenProvider.getAdminIdFromToken(token);
            // 4. 사용자 ID로 UserDetails 객체 조회 (AdminDetailService 사용)
            UserDetails userDetails = adminDetailsService.loadUserByUsername(adminId);
            // 5. Authentication 객체 생성 (사용자 정보, 비밀번호 null, 권한)
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            // 6. SecurityContext에 Authentication 객체 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // 7. 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }

}
