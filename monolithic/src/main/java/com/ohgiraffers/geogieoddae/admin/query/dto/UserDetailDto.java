package com.ohgiraffers.geogieoddae.admin.query.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDto {
    private Long userCode;
    private String userName;
    private String userEmail;
    private String userPhoneNumber;
    private String userAddress;
    private String userRole;
    private LocalDateTime createdAt;
    
    // 추가 정보
    private String customerKey;
    private String socialType;
}
