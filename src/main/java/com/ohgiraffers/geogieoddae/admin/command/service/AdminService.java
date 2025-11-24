package com.ohgiraffers.geogieoddae.admin.command.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ohgiraffers.geogieoddae.admin.command.dto.AdminLoginRequest;
import com.ohgiraffers.geogieoddae.admin.command.dto.AdminTokenResponse;
import com.ohgiraffers.geogieoddae.admin.command.entity.AdminEntity;
import com.ohgiraffers.geogieoddae.admin.command.repository.AdminRepository;
import com.ohgiraffers.geogieoddae.auth.command.entity.entrepreneur.EntrepreneurEntity;
import com.ohgiraffers.geogieoddae.auth.command.entity.entrepreneur.EntrepreneurStatus;
import com.ohgiraffers.geogieoddae.auth.command.repository.EntrepreneurRepository;
import com.ohgiraffers.geogieoddae.exception.AdminNotFoundException;
import com.ohgiraffers.geogieoddae.global.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final EntrepreneurRepository entrepreneurRepository; // EntrepreneurRepository 주입

    // 관리자 로그인
    public AdminTokenResponse login(AdminLoginRequest request) {
        // 1. 관리자 ID로 관리자 조회
        AdminEntity admin = adminRepository.findByAdminId(request.getAdminId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 관리자 ID입니다."));

        // 2. 비밀번호 검증
        if (!passwordEncoder.matches(request.getAdminPassword(), admin.getAdminPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 3. JWT 토큰 생성
        String accessToken = jwtTokenProvider.generateAdminAccessToken(admin.getAdminId());
        String refreshToken = jwtTokenProvider.generateAdminRefreshToken(admin.getAdminId());

        // 4. Refresh Token을 DB에 저장
        admin.setAdminRefreshToken(refreshToken);
        admin.setAdminRefreshTokenExpiresAt(jwtTokenProvider.getRefreshTokenExpiryAsLocalDateTime());
        adminRepository.save(admin);

        // 5. 로그인 성공 응답 반환
        return AdminTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // 관리자 로그아웃
    @Transactional
    public void logout(String adminId) {

        AdminEntity admin = adminRepository.findByAdminId(adminId)
                .orElseThrow(() -> new AdminNotFoundException("해당 관리자 ID를 찾을 수 없습니다: " + adminId));

                // 리프레시 토큰 무효화
        admin.setAdminRefreshToken(null);
        admin.setAdminRefreshTokenExpiresAt(null);

        adminRepository.save(admin);

    }

    // 리프레시 토큰으로 새 토큰 발급
    public AdminTokenResponse refreshToken(String refreshToken) {
        // 1. DB에서 해당 refreshToken을 가진 관리자 찾기
        Optional<AdminEntity> admin = adminRepository.findByAdminRefreshToken(refreshToken);

        if (admin.isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 리프레시 토큰입니다.");
        }

        // 2. refreshToken 만료 시간 확인
        if (admin.get().getAdminRefreshTokenExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("만료된 리프레시 토큰입니다.");
        }

        // 3. 새로운 access token과 refresh token 생성
        String newAccessToken = jwtTokenProvider.generateAdminAccessToken(admin.get().getAdminId());
        String newRefreshToken = jwtTokenProvider.generateAdminRefreshToken(admin.get().getAdminId());

        // 4. DB에 새로운 refresh token 저장
        admin.get().setAdminRefreshToken(newRefreshToken);
        admin.get().setAdminRefreshTokenExpiresAt(jwtTokenProvider.getRefreshTokenExpiryAsLocalDateTime());
        adminRepository.save(admin.get());

        // 5. 새로운 토큰들 반환
        return AdminTokenResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    // 관리자 사업자등록 신청 승인
    @Transactional
    public void approveEntrepreneur(Long id) {
        EntrepreneurEntity entrepreneur = entrepreneurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사업자 정보를 찾을 수 없습니다."));
        entrepreneur.setEntrepreneurStatus(EntrepreneurStatus.APPROVED);   // 사업자 등록 요청 승인
        entrepreneurRepository.save(entrepreneur);
    }

    // 관리자 사업자등록 신청 거절
    @Transactional
    public void rejectEntrepreneur(Long id) {
        EntrepreneurEntity entrepreneur = entrepreneurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사업자 정보를 찾을 수 없습니다."));
        entrepreneur.setEntrepreneurStatus(EntrepreneurStatus.REJECTED);  // 사업자 등록 요청 거절
        entrepreneurRepository.save(entrepreneur);
    }
}
