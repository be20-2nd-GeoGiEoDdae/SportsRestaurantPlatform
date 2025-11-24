package com.ohgiraffers.geogieoddae.auth.command.service;

import java.time.LocalDateTime;

import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohgiraffers.geogieoddae.auth.command.dto.SocialSignupRequest;
import com.ohgiraffers.geogieoddae.auth.command.dto.SocialSignupResponse;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.SocialEntity;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.SocialType;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserRole;
import com.ohgiraffers.geogieoddae.auth.command.repository.KakaoRepository;
import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import com.ohgiraffers.geogieoddae.global.jwt.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class SocialSignupService {
    private final UserRepository userRepository;
    private final KakaoRepository kakaoRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public SocialSignupResponse socialSignup(SocialSignupRequest request, Authentication authentication) {
        System.out.println("=== SocialSignupService.socialSignup() 시작 ===");
        
        // JWT 인증이 완료된 사용자라면 Authentication에서 사용자 정보 추출
        if (authentication != null && authentication.isAuthenticated() && 
            authentication.getPrincipal() instanceof UserEntity) {
            
            System.out.println("LOG: JWT 인증된 사용자로 추가정보 입력 처리");
            return updateAuthenticatedUser(request, (UserEntity) authentication.getPrincipal());
            
        } else {
            // 이메일 기반 fallback (개발/테스트 환경)
            System.out.println("LOG: 이메일 기반 fallback 처리");
            return updateUserByEmail(request);
        }
    }
    
    /**
     * JWT 인증된 사용자의 추가 정보 업데이트
     */
    private SocialSignupResponse updateAuthenticatedUser(SocialSignupRequest request, UserEntity user) {
        // 추가정보 직접 업데이트
        if (request.getUserName() != null && !request.getUserName().trim().isEmpty()) {
            user.setUserName(request.getUserName());
        }
        if (request.getUserPhoneNumber() != null && !request.getUserPhoneNumber().trim().isEmpty()) {
            user.setUserPhoneNumber(request.getUserPhoneNumber());
        }
        if (request.getUserAddress() != null && !request.getUserAddress().trim().isEmpty()) {
            user.setUserAddress(request.getUserAddress());
        }
        
        // customerKey가 없으면 생성
        if (user.getCustomerKey() == null || user.getCustomerKey().trim().isEmpty()) {
            user.setCustomerKey(UUID.randomUUID().toString());
            System.out.println("LOG: customerKey 생성 완료: " + user.getCustomerKey());
        }
        
        UserEntity savedUser = userRepository.save(user);
        System.out.println("LOG: 사용자 추가정보 업데이트 완료 - 사용자 ID: " + savedUser.getUserCode());
        
        return SocialSignupResponse.builder()
                .userCode(savedUser.getUserCode())
                .userName(savedUser.getUserName())
                .userEmail(savedUser.getUserEmail())
                .userPhoneNumber(savedUser.getUserPhoneNumber())
                .userAddress(savedUser.getUserAddress())
                .message("추가정보 입력이 완료되었습니다.")
                .build();
    }
    
    /**
     * 이메일 기반 사용자 정보 업데이트 (개발/테스트 환경)
     */
    private SocialSignupResponse updateUserByEmail(SocialSignupRequest request) {
        // 기존 사용자 조회 후 업데이트
        UserEntity existingUser = userRepository.findByUserEmail(request.getUserEmail())
                .orElse(null);
        
        if (existingUser != null) {
            // 기존 사용자 업데이트
            System.out.println("LOG: 기존 사용자 정보 업데이트");
            return updateAuthenticatedUser(request, existingUser);
        } else {
            // 신규 사용자 생성 (테스트 환경)
            System.out.println("LOG: 신규 사용자 생성 (테스트 환경)");
            return createNewTestUser(request);
        }
    }
    
    /**
     * 테스트 환경에서 신규 사용자 생성
     */
    private SocialSignupResponse createNewTestUser(SocialSignupRequest request) {
        String userEmail = request.getUserEmail();
        String socialId = request.getSocialId();
        SocialType socialType = SocialType.valueOf(request.getSocialType());
        
        // 1. member DB에 신규 회원 저장
        UserEntity user = UserEntity.builder()
                .userEmail(userEmail)
                .userName(request.getUserName())
                .userPhoneNumber(request.getUserPhoneNumber())
                .userAddress(request.getUserAddress())
                .userRole(UserRole.USER)
                .userRefreshToken("initial_token")
                .userRefreshTokenExpiresAt(LocalDateTime.now())
                .customerKey(UUID.randomUUID().toString())
                .build();
        UserEntity savedUser = userRepository.save(user);

        // 2. social DB에 소셜 정보 저장
        SocialEntity social = SocialEntity.builder()
                .socialId(socialId)
                .socialType(socialType)
                .member(savedUser)
                .build();
        kakaoRepository.save(social);
        
        return SocialSignupResponse.builder()
                .userCode(savedUser.getUserCode())
                .userName(savedUser.getUserName())
                .userEmail(savedUser.getUserEmail())
                .userPhoneNumber(savedUser.getUserPhoneNumber())
                .userAddress(savedUser.getUserAddress())
                .message("테스트 환경에서 회원가입이 완료되었습니다.")
                .build();
    }
}
