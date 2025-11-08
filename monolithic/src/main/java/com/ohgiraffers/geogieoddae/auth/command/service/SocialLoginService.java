package com.ohgiraffers.geogieoddae.auth.command.service;

import org.springframework.stereotype.Service;

import com.ohgiraffers.geogieoddae.auth.command.dto.KakaoUserInfoDto;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserRole;
import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SocialLoginService {
    
    private final UserRepository userRepository;

    public UserEntity findOrCreateUserByKakao(KakaoUserInfoDto kakaoUserInfo) {
        UserEntity user = userRepository.findByUserEmail(kakaoUserInfo.getEmail())
            .orElse(null);
        if (user == null) {
            user = UserEntity.builder()
                .userEmail(kakaoUserInfo.getEmail())
                .userName(kakaoUserInfo.getNickname())
                .userRole(UserRole.USER)
                // 기타 필드 초기화
                .build();
            userRepository.save(user);
        }
        return user;
    }
}
