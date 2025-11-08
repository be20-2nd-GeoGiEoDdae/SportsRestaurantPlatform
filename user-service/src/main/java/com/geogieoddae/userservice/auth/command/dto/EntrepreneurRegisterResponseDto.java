package com.geogieoddae.userservice.auth.command.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class EntrepreneurRegisterResponseDto {
    private Long entrepreneurCode;
    private Integer entrepreneurId;
    private String entrepreneurCertificateUrl;
    private String entrepreneurBankAccount;
    private Long userCode;
}
