package com.ohgiraffers.geogieoddae.admin.command.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohgiraffers.geogieoddae.admin.command.dto.AdminLoginRequest;
import com.ohgiraffers.geogieoddae.admin.command.dto.AdminRefreshTokenRequest;
import com.ohgiraffers.geogieoddae.admin.command.dto.AdminTokenResponse;
import com.ohgiraffers.geogieoddae.admin.command.security.AdminDetails;
import com.ohgiraffers.geogieoddae.admin.command.service.AdminService;
import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AdminTokenResponse>> login(@RequestBody AdminLoginRequest request) {
        AdminTokenResponse response = adminService.login(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(Authentication authentication) {
        
        AdminDetails adminDetails = (AdminDetails) authentication.getPrincipal();
        adminService.logout(adminDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success(null));
        
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<AdminTokenResponse>> refresh(@RequestBody AdminRefreshTokenRequest request) {
        AdminTokenResponse response = adminService.refreshToken(request.getAdminRefreshToken());
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PostMapping("/entrepreneur/{id}/approve")
    public ResponseEntity<ApiResponse<Void>> approveEntrepreneur(@PathVariable Long id) {
        adminService.approveEntrepreneur(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PostMapping("/entrepreneur/{id}/reject")
    public ResponseEntity<ApiResponse<Void>> rejectEntrepreneur(@PathVariable Long id) {
        adminService.rejectEntrepreneur(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

}
