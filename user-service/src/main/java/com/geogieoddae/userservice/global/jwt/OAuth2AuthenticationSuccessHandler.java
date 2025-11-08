package com.geogieoddae.userservice.global.jwt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.geogieoddae.userservice.auth.command.entity.user.UserEntity;
import com.geogieoddae.userservice.auth.command.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("LOG: OAuth2AuthenticationSuccessHandler.onAuthenticationSuccess 실행됨");

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        // String email = oAuth2User.getAttribute("email");
        Map<String, Object> attributes = oAuth2User.getAttributes();
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        String email = (String) kakaoAccount.get("email");

        UserEntity user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 사용자 role에 따라 토큰 발급 방식 분기
        String accessToken;
        String refreshToken;
        if (user.getUserRole().name().equals("ADMIN")) {   // 관리자인 토큰 발급
            accessToken = jwtTokenProvider.generateAdminAccessToken(user.getUserEmail());
            refreshToken = jwtTokenProvider.generateAdminRefreshToken(user.getUserEmail());
        } else {                    // 이용자인 경우
            accessToken = jwtTokenProvider.generateUserAccessToken(user.getUserCode().toString(), user.getUserRole().name());
            refreshToken = jwtTokenProvider.generateUserRefreshToken(user.getUserCode().toString());
        }

        // RefreshToken/만료시간 모두 저장
        user.setUserRefreshToken(refreshToken);
        user.setUserRefreshTokenExpiresAt(jwtTokenProvider.getRefreshTokenExpiryAsLocalDateTime());
        userRepository.save(user);

        String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:3000/oauth/redirect")
                .queryParam("accessToken", accessToken)
                .queryParam("refreshToken", refreshToken)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUriString();

        System.out.println("LOG: 리다이렉트 URL 생성 완료: " + targetUrl);

        response.sendRedirect(targetUrl);
    }
}
