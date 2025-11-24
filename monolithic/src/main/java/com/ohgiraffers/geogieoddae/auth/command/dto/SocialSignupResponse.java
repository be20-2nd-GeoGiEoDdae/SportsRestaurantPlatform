package com.ohgiraffers.geogieoddae.auth.command.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SocialSignupResponse {
    private Long userCode;
    private String userEmail;
    private String userName;
    private String userPhoneNumber;
    private String userAddress;
    private String message;
    
    public static SocialSignupResponse of(Long userCode, String userEmail, String userName, 
                                        String userPhoneNumber, String userAddress) {
        return SocialSignupResponse.builder()
                .userCode(userCode)
                .userEmail(userEmail)
                .userName(userName)
                .userPhoneNumber(userPhoneNumber)
                .userAddress(userAddress)
                .message("개인정보 입력이 완료되었습니다.")
                .build();
    }
}
