package com.ohgiraffers.geogieoddae.global.jwt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.SocialEntity;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.SocialType;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.auth.command.repository.KakaoRepository;
import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final KakaoRepository kakaoRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("=== OAuth2 로그인 성공 디버그 시작 ===");
        
        try {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            Map<String, Object> attributes = oAuth2User.getAttributes();
            System.out.println("1. 카카오 전체 응답: " + attributes);

            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            System.out.println("2. kakao_account: " + kakaoAccount);

            String email = (String) kakaoAccount.get("email");
            System.out.println("3. 추출된 이메일: " + email);

            String socialId = attributes.get("id").toString();
            System.out.println("4. 추출된 소셜 ID: " + socialId);

            // 사용자 조회 (CustomOAuth2UserService에서 이미 생성했으므로 존재해야 함)
            Optional<UserEntity> userOptional = userRepository.findByUserEmail(email);

            // 탈퇴 체크 추가
            if (userOptional.isPresent()) {
                UserEntity user = userOptional.get();
                if (user.getIsWithdrawn()) {
                    // 탈퇴한 회원인 경우 에러 페이지로 리다이렉트
                    response.sendRedirect("http://localhost:8080/login?error=withdrawn_user&message=탈퇴한 회원입니다");
                    return;
                }
            }

            if (userOptional.isEmpty()) {
                System.out.println("❌ 사용자를 찾을 수 없습니다. CustomOAuth2UserService에서 생성되지 않았나요?");
                response.sendRedirect("http://localhost:8080/login?error=user_not_found");
                return;
            }
            UserEntity user = userOptional.get();
            System.out.println("5. DB에서 사용자 찾음: " + user.getUserEmail());

            // 소셜 정보 조회
            Optional<SocialEntity> socialOptional = kakaoRepository.findBySocialIdAndSocialType(socialId, SocialType.KAKAO);
            if (socialOptional.isEmpty()) {
                System.out.println("❌ 소셜 정보를 찾을 수 없습니다. CustomOAuth2UserService에서 생성되지 않았나요?");
                response.sendRedirect("http://localhost:5173/login?error=social_not_found");
                return;
            }
            SocialEntity socialEntity = socialOptional.get();
            System.out.println("6. DB에서 소셜 정보 찾음: " + socialEntity.getSocialId());

            System.out.println("7. JWT 토큰 생성 시작...");

        // 회원 여부 판단: 이메일이 DB에 존재하는지 확인
        boolean isNewUser = user.getUserCode() == null; // saveOrUpdate에서 새로 생성된 경우 확인 필요
        
        // 신규 회원 판단: 임시값이면 추가정보 입력이 필요한 상태
        boolean isSignupComplete = user.getUserName() != null && 
                                  !user.getUserName().equals("TEMP_USER") && 
                                  !user.getUserName().trim().isEmpty() &&
                                  user.getUserPhoneNumber() != null && 
                                  !user.getUserPhoneNumber().equals("TEMP_PHONE") && 
                                  !user.getUserPhoneNumber().trim().isEmpty() &&
                                  user.getUserAddress() != null && 
                                  !user.getUserAddress().trim().isEmpty();
        
        System.out.println("LOG: 회원가입 완료 여부: " + isSignupComplete);
        
        String targetUrl;
        
        if (!isSignupComplete) {
            // 신규 회원 또는 추가정보 미입력 회원 - 추가정보 입력 페이지로
            String tempAccessToken = jwtTokenProvider.generateUserAccessTokenWithSocial(
                user.getUserCode().toString(), 
                user.getUserRole().name(),
                user.getUserEmail(),
                socialEntity.getSocialId(),
                socialEntity.getSocialType().name()
            );
            
            targetUrl = UriComponentsBuilder.fromUriString("http://localhost:5173/signup/additional")
                    .queryParam("tempToken", tempAccessToken)
                    .queryParam("email", user.getUserEmail())
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUriString();
                    
            System.out.println("LOG: 신규 회원 - 추가정보 입력 페이지로 리다이렉트");
            
        } else {
            // 추가정보가 이미 있는 경우 - 정식 로그인 완료
            String accessToken;
            String refreshToken;
            
            if (user.getUserRole().name().equals("ADMIN")) {
                accessToken = jwtTokenProvider.generateAdminAccessToken(user.getUserEmail());
                refreshToken = jwtTokenProvider.generateAdminRefreshToken(user.getUserEmail());
            } else {
                accessToken = jwtTokenProvider.generateUserAccessTokenWithSocial(
                    user.getUserCode().toString(), 
                    user.getUserRole().name(),
                    user.getUserEmail(),
                    socialEntity.getSocialId(),
                    socialEntity.getSocialType().name()
                );
                refreshToken = jwtTokenProvider.generateUserRefreshToken(user.getUserCode().toString());
            }

            // RefreshToken 저장
            user.setUserRefreshToken(refreshToken);
            user.setUserRefreshTokenExpiresAt(jwtTokenProvider.getRefreshTokenExpiryAsLocalDateTime());
            userRepository.save(user);

            targetUrl = UriComponentsBuilder.fromUriString("http://localhost:5173/login/success")
                    .queryParam("accessToken", accessToken)
                    .queryParam("refreshToken", refreshToken)
                    .build()
                    .encode(StandardCharsets.UTF_8)
                    .toUriString();
                    
            System.out.println("LOG: 로그인 완료 - 메인 페이지로 리다이렉트");
        }

            System.out.println("8. 리다이렉트 URL 생성 완료: " + targetUrl);
            System.out.println("=== OAuth2 로그인 성공 디버그 완료 ===");

            response.sendRedirect(targetUrl);
            
        } catch (Exception e) {
            System.out.println("❌ OAuth2 에러 발생: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect("http://localhost:8080/login?error=oauth_failed");
            return;
        }
    }
}
