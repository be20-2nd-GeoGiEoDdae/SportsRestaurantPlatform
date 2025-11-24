package com.ohgiraffers.geogieoddae.auth.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EntrepreneurRegisterRequestDto {

    private Integer entrepreneurId;
    private String entrepreneurCertificateUrl;
    private Integer entrepreneurBankAccount;
    private Long userCode;      // 회원 PK만 받음

}
