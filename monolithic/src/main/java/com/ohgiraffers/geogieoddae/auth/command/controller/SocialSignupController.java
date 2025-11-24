package com.ohgiraffers.geogieoddae.auth.command.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ohgiraffers.geogieoddae.auth.command.dto.SocialSignupRequest;
import com.ohgiraffers.geogieoddae.auth.command.dto.SocialSignupResponse;
import com.ohgiraffers.geogieoddae.auth.command.service.SocialSignupService;
import com.ohgiraffers.geogieoddae.auth.command.service.SmsVerificationService;
import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Map;


@Tag(name = "소셜 회원가입 api")
@Controller  // RestController에서 Controller로 변경
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class SocialSignupController {

    private final SocialSignupService socialSignupService;
    private final SmsVerificationService smsVerificationService;  // 추가

    /**
     * 소셜 로그인 후 추가 개인정보 입력 페이지
     */
    @GetMapping("/signup/additional")
    public String additionalInfoPage(
            @RequestParam(required = false) String tempToken,
            @RequestParam(required = false) String email,
            Model model) {
        
        model.addAttribute("tempToken", tempToken);
        model.addAttribute("email", email);
        
        return "auth/signup-additional";
    }

    @PostMapping("/social-signup")
    public ResponseEntity<ApiResponse<SocialSignupResponse>> socialSignup(
            @Valid @RequestBody SocialSignupRequest request,
            Authentication authentication) {
        
        // JWT 토큰이 있으면 토큰에서 소셜 정보 추출, 없으면 request에서 사용
        SocialSignupResponse response = socialSignupService.socialSignup(request, authentication);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * SMS 인증번호 발송 API
     */
    @PostMapping("/sms/send")
    public ResponseEntity<ApiResponse<String>> sendSmsVerification(
            @RequestBody Map<String, String> request) {
        
        String phoneNumber = request.get("phoneNumber");
        
        if (phoneNumber == null || !phoneNumber.matches("^010-\\d{4}-\\d{4}$")) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.failure("INVALID_PHONE", "올바른 전화번호 형식이 아닙니다."));
        }
        
        boolean success = smsVerificationService.sendVerificationCode(phoneNumber);
        
        if (success) {
            return ResponseEntity.ok(ApiResponse.success("인증번호가 발송되었습니다."));
        } else {
            return ResponseEntity.status(500)
                    .body(ApiResponse.failure("SMS_SEND_FAILED", "인증번호 발송에 실패했습니다."));
        }
    }
    
    /**
     * SMS 인증번호 확인 API
     */
    @PostMapping("/sms/verify")
    public ResponseEntity<ApiResponse<String>> verifySmsCode(
            @RequestBody Map<String, String> request) {
        
        String phoneNumber = request.get("phoneNumber");
        String verificationCode = request.get("verificationCode");
        
        if (phoneNumber == null || verificationCode == null) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.failure("MISSING_PARAMS", "전화번호와 인증번호를 입력해주세요."));
        }
        
        boolean isValid = smsVerificationService.verifyCode(phoneNumber, verificationCode);
        
        if (isValid) {
            return ResponseEntity.ok(ApiResponse.success("인증이 완료되었습니다."));
        } else {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.failure("INVALID_CODE", "인증번호가 올바르지 않거나 만료되었습니다."));
        }
    }
}