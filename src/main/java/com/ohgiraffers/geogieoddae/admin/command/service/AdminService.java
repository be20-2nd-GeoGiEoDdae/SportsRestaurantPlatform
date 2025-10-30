package com.ohgiraffers.geogieoddae.admin.command.service;

import com.ohgiraffers.geogieoddae.admin.command.dto.AdminLoginRequest;
import com.ohgiraffers.geogieoddae.admin.command.dto.AdminTokenResponse;
import com.ohgiraffers.geogieoddae.admin.command.entity.AdminEntity;
import com.ohgiraffers.geogieoddae.admin.command.repository.AdminRepository;
import com.ohgiraffers.geogieoddae.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;  // 비밀번호 암호화/검증
    private final JwtTokenProvider jwtTokenProvider;  // JWT 토큰 생성 유틸

    // 관리자 로그인 - DTO 를 받아서 토큰 응답 반환
    public AdminTokenResponse login(AdminLoginRequest request) {
        // 1. 관리자 ID로 관리자 조회
        AdminEntity admin = adminRepository.findByAdminId(request.getAdminId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 관리자 ID입니다."));

        // 2. 비밀번호 검증
        if (!passwordEncoder.matches(request.getAdminPassword(), admin.getAdminPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 3. JWT 토큰 생성
        String accessToken = jwtTokenProvider.generateAccessToken(admin.getAdminId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(admin.getAdminId());

        // 4. Refresh Token 을 DB에 저장
        admin.setAdminRefreshToken(refreshToken);
        admin.setAdminRefreshTokenExpiresAt(jwtTokenProvider.getRefreshTokenExpiryAsLocalDateTime());
        adminRepository.save(admin);

        // 5. 로그인 성공 응답 반환
        return AdminTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // 관리자 로그아웃 - user
    public void logout(String adminId) {
        // 로그아웃 로직
    }

    // 리프레시 토큰으로 새 토큰 발급
    public AdminTokenResponse refreshToken(String refreshToken) {
        // 1. refreshToken 이 유효한지 검증
        // 2. DB에서 해당 refreshToken을 가진 관리자 찾기
        Optional<AdminEntity> admin = adminRepository.findByAdminRefreshToken(refreshToken);

        if (admin.isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 리프레시 토큰입니다.");
        }

        // 3. refreshToken 만료 시간 확인
        if (admin.get().getAdminRefreshTokenExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("만료된 리프레시 토큰입니다.");
        }

        // 4. 새로운 access token과 refresh token 생성
        String newAccessToken = jwtTokenProvider.generateAccessToken(admin.get().getAdminId());
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(admin.get().getAdminId());

        // 5. DB 에 새로운 refresh token 저장
        admin.get().setAdminRefreshToken(newRefreshToken);
        admin.get().setAdminRefreshTokenExpiresAt(LocalDateTime.now().plusDays(7));
        adminRepository.save(admin.get());

        // 6. 새로운 토큰들 반환
        return AdminTokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
}
