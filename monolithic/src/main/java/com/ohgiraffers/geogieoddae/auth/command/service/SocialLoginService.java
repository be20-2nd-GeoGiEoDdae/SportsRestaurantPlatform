package com.ohgiraffers.geogieoddae.auth.command.service;

import java.util.Optional;

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

        Optional<UserEntity> userOptional = userRepository.findByUserEmail(kakaoUserInfo.getEmail());
        
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            
            // 탈퇴 체크 추가
            if (user.getIsWithdrawn()) {
                throw new RuntimeException("탈퇴한 회원입니다. 다시 가입해주세요.");
            }
            
            return user;
        } else {
            // 회원이 없으면 새로 생성
            UserEntity newUser = UserEntity.builder()
                .userEmail(kakaoUserInfo.getEmail())
                .userName(kakaoUserInfo.getNickname())
                .userRole(UserRole.USER)
                .isWithdrawn(false)  // 기본값 설정
                .build();
            return userRepository.save(newUser);
        }
    }
}
