package com.ohgiraffers.geogieoddae.auth.command.service;

import org.springframework.stereotype.Service;

import com.ohgiraffers.geogieoddae.auth.command.dto.UserUpdateRequestDto;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;

import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void updateUserInfo(String userId, UserUpdateRequestDto request) {

        UserEntity user = userRepository.findById(Long.valueOf(userId))
            .orElseThrow(()->new IllegalArgumentException("존재하지 않는 회원입니다."));
        user.setUserName(request.getUserName());
        user.setUserEmail(request.getuserEmail());
        user.setUserPhoneNumber(request.getUserPhoneNumber);
        userRepository.save(user);
        
    }
}
