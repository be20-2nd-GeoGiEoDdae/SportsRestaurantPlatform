/* JWT 인증 필터 - 요청 시 JWT 유효성 검사 및 인증객체 등록
 * @Author : 김성현
 * @Date : 2025-10-31
 * @Version : 1.0
 */

package com.ohgiraffers.geogieoddae.global.jwt;

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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

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
                    UserEntity user = userRepository.findById(Long.valueOf(id))
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
                logger.error("JWT 필터 처리 중 오류 발생", e);
            }
        }

        filterChain.doFilter(request, response);
    }
}
