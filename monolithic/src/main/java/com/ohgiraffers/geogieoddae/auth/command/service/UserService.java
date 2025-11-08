package com.ohgiraffers.geogieoddae.auth.command.service;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserRole;
import org.springframework.stereotype.Service;

import com.ohgiraffers.geogieoddae.auth.command.dto.UserUpdateRequestDto;
import com.ohgiraffers.geogieoddae.auth.command.dto.UserUpdateResponseDto;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserUpdateResponseDto updateUserInfo(String userId, UserUpdateRequestDto request) {
        UserEntity user = userRepository.findById(Long.valueOf(userId))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        user.setUserName(request.getUserName());
        user.setUserPhoneNumber(request.getUserPhoneNumber());
        user.setUserAddress(request.getUserAddress());
        userRepository.save(user);

        return new UserUpdateResponseDto(
                user.getUserName(),
                user.getUserEmail(),
                user.getUserPhoneNumber(),
                user.getUserAddress(),
                user.getUserRole()
        );
    }

    public UserEntity updateRole(Long userId, String newRole) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUserRole(UserRole.valueOf(newRole.toUpperCase())); // ex. "OWNER"
        return userRepository.save(user);
    }

    @Transactional
    public UserEntity saveUser(UserEntity updatedUser) {
        return userRepository.save(updatedUser);
    }
}
