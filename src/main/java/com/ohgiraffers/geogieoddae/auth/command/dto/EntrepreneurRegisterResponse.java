package com.ohgiraffers.geogieoddae.auth.command.dto;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EntrepreneurRegisterResponse {
    private Long entrepreneurCode;
    private Integer entrepreneurId;
    private String entrepreneurCertificateUrl;
    private String entrepreneurBankAccount;
    private Long userCode;
}
