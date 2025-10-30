package com.ohgiraffers.geogieoddae.admin.command.service;

import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Override
    public boolean login(String adminId, String adminPassword) {
        // DB 에서 adminId, adminPassword로 관리자 조회 후 검증
        // 예시: return adminRepository.existsByAdminAndAdminPassword(adminId, adminPassword);
        
        return true;  // 실제 구현 필요
    }

    @Override
    public void logout() {
        // 세션/토큰 무효화 등 로그아웃 처리
    }
}
