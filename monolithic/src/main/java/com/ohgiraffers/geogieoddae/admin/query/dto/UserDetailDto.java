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

    // 사업자 추가 정보
    private Long entrepreneurCode;
    private Integer entrepreneurId;                      // 사업자등록번호를 entrepreneur_id에 저장 중이면 Integer
    private String entrepreneurCertificateUrl;
    private Integer entrepreneurBankAccount;             // 엔티티가 Integer라면 Integer, 나중에 String으로 바꾸려면 함께 변경
    private String entrepreneurStatus;                   // "WAITING"/"APPROVED"/"REJECTED"
}
