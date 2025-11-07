package com.ohgiraffers.geogieoddae.auth.command.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ohgiraffers.geogieoddae.auth.command.dto.SocialSignupRequest;
import com.ohgiraffers.geogieoddae.auth.command.service.SocialSignupService;
import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;

import lombok.RequiredArgsConstructor;


@Tag(name = "소셜 회원가입 api")
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