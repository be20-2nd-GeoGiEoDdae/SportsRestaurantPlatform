package com.ohgiraffers.geogieoddae.auth.command.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SocialSignupRequest {
    private String userEmail;            // 소셜에서 받은 이메일
    private String userName;             // 추가 입력
    private String userPhoneNumber;      // 추가 입력
    private String userAddress;          // 추가 입력
    private String socialId;             // 소셜 ID
    private String socialType;           // KAKAO, NAVER, GOOGLE 등
}
