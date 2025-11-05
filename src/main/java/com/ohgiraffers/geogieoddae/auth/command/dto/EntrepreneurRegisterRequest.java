package com.ohgiraffers.geogieoddae.auth.command.dto;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EntrepreneurRegisterRequest {

    private Long entrepreneurCode;
    private Integer entrepreneurId;
    private String entrepreneurCertificateUrl;
    private String entrepreneurBankAccount;
    private UserEntity user;
}
