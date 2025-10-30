package com.ohgiraffers.geogieoddae.admin.command.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohgiraffers.geogieoddae.admin.command.dto.AdminLoginRequest;
import com.ohgiraffers.geogieoddae.admin.command.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminLoginRequest request) {
        boolean success = adminService.login(request.getAdminId(), request.getAdminPassword());  // 의존성 주입
        if (success) {
            // 토큰 발급 등 추가 구현 필요
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }        
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
        adminService.logout(); // 의존성 주입 - 토큰 무효화 등 추가 구현 필요
        return ResponseEntity.ok("로그아웃 성공");
    }
}
