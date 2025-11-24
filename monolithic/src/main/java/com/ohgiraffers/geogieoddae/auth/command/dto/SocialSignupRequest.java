package com.ohgiraffers.geogieoddae.auth.command.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SocialSignupRequest {
    
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String userEmail;            // 소셜에서 받은 이메일
    
    @NotBlank(message = "이름은 필수입니다.")
    private String userName;             // 추가 입력
    
    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "전화번호는 010-1234-5678 형식이어야 합니다.")
    private String userPhoneNumber;      // 추가 입력
    
    @NotBlank(message = "주소는 필수입니다.")
    private String userAddress;          // 추가 입력
    
    private String socialId;             // 소셜 ID
    private String socialType;           // KAKAO, NAVER, GOOGLE 등
}
