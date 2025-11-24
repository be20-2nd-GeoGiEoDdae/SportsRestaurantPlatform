/* JWT 인증 필터 - 요청 시 JWT 유효성 검사 및 인증객체 등록
 * @Author : 김성현
 * @Date : 2025-10-31
 * @Version : 1.0
 */

package com.ohgiraffers.geogieoddae.global.jwt;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ohgiraffers.geogieoddae.admin.command.security.AdminDetails;
import com.ohgiraffers.geogieoddae.admin.command.security.AdminDetailsService;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final AdminDetailsService adminDetailsService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // OAuth2 로그인 과정에서는 JWT 토큰이 아직 생성되지 않았으므로 JWT 필터를 스킵
        String requestURI = request.getRequestURI();
        System.out.println("🔍 JWT 필터 처리 요청 URI: " + requestURI);
        
        // 정적 리소스 및 OAuth2 경로는 JWT 필터 제외
        if (requestURI.startsWith("/oauth2/") ||                    // OAuth2 인증 시작
            requestURI.startsWith("/login/oauth2/code/") ||         // OAuth2 콜백 처리
            requestURI.startsWith("/login") ||                      // 로그인 페이지 및 에러 페이지
            requestURI.equals("/h2-console") ||                     // H2 콘솔
            requestURI.startsWith("/h2-console/") ||                // H2 콘솔 리소스
            requestURI.equals("/api/admin/login") ||                // 관리자 로그인 (JWT 생성 전)
            requestURI.equals("/api/admin/refresh") ||              // 토큰 재발급
            requestURI.equals("/favicon.ico") ||                    // 파비콘
            requestURI.startsWith("/css/") ||                       // CSS 파일
            requestURI.startsWith("/js/") ||                        // JS 파일
            requestURI.startsWith("/images/") ||                    // 이미지 파일
            requestURI.startsWith("/static/") ||                    // 정적 리소스
            requestURI.endsWith(".css") ||                          // CSS 파일 확장자
            requestURI.endsWith(".js") ||                           // JS 파일 확장자
            requestURI.endsWith(".ico") ||                          // 아이콘 파일
            requestURI.endsWith(".png") ||                          // 이미지 파일
            requestURI.endsWith(".jpg") ||                          // 이미지 파일
            requestURI.endsWith(".jpeg") ||                         // 이미지 파일
            requestURI.endsWith(".gif") ||                          // 이미지 파일
            requestURI.endsWith(".svg")) {                          // SVG 파일
            
            System.out.println("🔄 JWT 필터 스킵 - 경로: " + requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        String token = jwtTokenProvider.resolveToken(request);
        
        // 추가정보 입력 페이지의 경우 쿼리 파라미터에서 tempToken 확인
        if (token == null && requestURI.equals("/api/auth/signup/additional")) {
            String tempToken = request.getParameter("tempToken");
            if (tempToken != null) {
                token = tempToken;
                System.out.println("🔗 쿼리 파라미터에서 tempToken 발견: " + tempToken.substring(0, Math.min(20, tempToken.length())) + "...");
            }
        }

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

                String id = claims.getSubject();
                String role = claims.get("role", String.class);

                // adminId & role 로그
                System.out.println("Id : " + id + " ");
                System.out.println("role : " + role + " ");

                // DB에서 AdminDetails 로드
                Object principal;
                Collection<? extends GrantedAuthority> authorities;

                if ("ROLE_ADMIN".equals(role)) {
                    // ✅ 관리자 토큰이면 관리자 서비스로 인증
                    AdminDetails adminDetails = (AdminDetails) adminDetailsService.loadUserByUsername(id);
                    principal = adminDetails;
                    authorities = adminDetails.getAuthorities();

                } else {
                    // ✅ 일반 사용자(소셜 로그인 유저)
                    UserEntity user = userRepository.findById(Long.parseLong(id))
                            .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + id));

                    principal = user; // 바로 엔티티 사용 (UserDetails 아님)
                    authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getUserRole().name()));
                }
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        principal,
                        null,
                        authorities
                );
                // 인증객체 로그
                System.out.println("authentication : " + authentication + " ");

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                System.err.println("JWT 필터 처리 중 오류 발생: " + e.getMessage());
                e.printStackTrace();
            }
        }

        filterChain.doFilter(request, response);
    }
}
