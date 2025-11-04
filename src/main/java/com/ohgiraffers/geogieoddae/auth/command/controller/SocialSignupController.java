package com.ohgiraffers.geogieoddae.auth.command.controller;

import com.ohgiraffers.geogieoddae.auth.command.dto.SocialSignupRequest;
import com.ohgiraffers.geogieoddae.auth.command.service.SocialSignupService;
import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class SocialSignupController {

    private final SocialSignupService socialSignupService;

    public SocialSignupController(SocialSignupService socialSignupService) {
        this.socialSignupService = socialSignupService;
    }

    @PostMapping("/social-signup")
    public ResponseEntity<ApiResponse> socialSignup(@RequestBody SocialSignupRequest request) {
        socialSignupService.socialSignup(request);
        return ResponseEntity.ok(ApiResponse.success("회원가입 완료"));
    }
}