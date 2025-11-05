package com.ohgiraffers.geogieoddae.auth.command.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EntrepreneurRegisterResponseDto {
    private Long entrepreneurCode;
    private Integer entrepreneurId;
    private String entrepreneurCertificateUrl;
    private String entrepreneurBankAccount;
    private Long userCode;
}
