package com.ohgiraffers.geogieoddae.global.jwt;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("LOG: OAuth2AuthenticationSuccessHandler.onAuthenticationSuccess 실행됨");

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> kakaoAccount = (Map<String, Object>) oAuth2User.getAttributes().get("kakao_account");
        String email = kakaoAccount != null ? (String)kakaoAccount.get("email") : null;

        UserEntity user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        String accessToken = jwtTokenProvider.generateAccessTokenUser(user.getUserEmail());
        String refreshToken = jwtTokenProvider.generateRefreshTokenUser(user.getUserEmail());

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

        // response.sendRedirect()를 직접 사용하여 리다이렉트
        response.sendRedirect(targetUrl);
    }
}
