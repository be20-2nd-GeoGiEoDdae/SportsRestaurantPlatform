package com.ohgiraffers.geogieoddae.auth.command.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ohgiraffers.geogieoddae.auth.command.dto.UserUpdateRequestDto;
import com.ohgiraffers.geogieoddae.auth.command.dto.UserUpdateResponseDto;
import com.ohgiraffers.geogieoddae.auth.command.dto.UserWithdrawRequestDto;
import com.ohgiraffers.geogieoddae.auth.command.service.UserService;
import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "유저 정보 업데이트 api")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @PutMapping("/api/user/update")
    public ResponseEntity<ApiResponse<UserUpdateResponseDto>> updateUserInfo(
        @RequestBody UserUpdateRequestDto request,
        Authentication authentication
    ) {
        // 인증된 회원 정보에서 PK 추출
        String userId = authentication.getName();
        UserUpdateResponseDto updatedUser = userService.updateUserInfo(userId, request);
        return ResponseEntity.ok(ApiResponse.success(updatedUser));
    }

    @DeleteMapping("/api/user/withdraw")
    public ResponseEntity<ApiResponse<String>> withdrawUser(
        @RequestBody UserWithdrawRequestDto request,
        Authentication authentication
    ) {
        String userId = authentication.getName();
        userService.withdrawUser(userId, request.getWithdrawReason());
        return ResponseEntity.ok(ApiResponse.success("회원 탈퇴가 완료되었습니다."));
    }
    
}
