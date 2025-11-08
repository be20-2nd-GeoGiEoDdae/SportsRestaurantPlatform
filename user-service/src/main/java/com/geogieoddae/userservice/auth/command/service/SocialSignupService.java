package com.geogieoddae.userservice.auth.command.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geogieoddae.userservice.auth.command.dto.SocialSignupRequest;
import com.geogieoddae.userservice.auth.command.entity.user.SocialEntity;
import com.geogieoddae.userservice.auth.command.entity.user.SocialType;
import com.geogieoddae.userservice.auth.command.entity.user.UserEntity;
import com.geogieoddae.userservice.auth.command.entity.user.UserRole;
import com.geogieoddae.userservice.auth.command.repository.KakaoRepository;
import com.geogieoddae.userservice.auth.command.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SocialSignupService {
    private final UserRepository userRepository;
    private final KakaoRepository kakaoRepository;

    @Transactional
    public void socialSignup(SocialSignupRequest request) {
        // 1. member DB에 신규 회원 저장
        UserEntity user = UserEntity.builder()
                .userEmail(request.getUserEmail())
                .userName(request.getUserName())
                .userPhoneNumber(request.getUserPhoneNumber())
                .userAddress(request.getUserAddress())
                .userRole(UserRole.USER) // 기본값
                .userRefreshToken("initial_token")
                .userRefreshTokenExpiresAt(LocalDateTime.now())
                .build();
        userRepository.save(user);

        // 2. social DB에 소셜 정보 저장
        SocialEntity social = SocialEntity.builder()
                .socialId(request.getSocialId())
                .socialType(SocialType.valueOf(request.getSocialType()))
                .member(user)
                .build();
        kakaoRepository.save(social);
    }
}
