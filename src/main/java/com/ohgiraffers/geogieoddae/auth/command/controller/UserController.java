package com.ohgiraffers.geogieoddae.auth.command.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ohgiraffers.geogieoddae.auth.command.dto.UserUpdateRequestDto;
import com.ohgiraffers.geogieoddae.auth.command.service.UserService;
import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RestController
public class UserController {

    @PutMapping("/api/user/update")
    public ResponseEntity<ApiResponse<Void>> updateUserInfo(
        @RequestBody UserUpdateRequestDto request,
        Authentication authentication
    ) {
        // 인증된 회원 정보에서 PK 추출
        String userId = authentication.getName();
        UserService.updateUserInfo(userId, request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
    

}
