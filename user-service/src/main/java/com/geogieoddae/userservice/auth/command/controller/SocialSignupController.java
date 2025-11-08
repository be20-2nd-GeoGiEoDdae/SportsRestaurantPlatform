package com.geogieoddae.userservice.auth.command.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geogieoddae.userservice.auth.command.dto.SocialSignupRequest;
import com.geogieoddae.userservice.auth.command.service.SocialSignupService;
import com.geogieoddae.userservice.global.common.dto.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class SocialSignupController {

    private final SocialSignupService socialSignupService;

    @PostMapping("/social-signup")
    public ResponseEntity<ApiResponse<String>> socialSignup(@RequestBody SocialSignupRequest request) {
        socialSignupService.socialSignup(request);
        return ResponseEntity.ok(ApiResponse.success("회원가입 완료"));
    }
}