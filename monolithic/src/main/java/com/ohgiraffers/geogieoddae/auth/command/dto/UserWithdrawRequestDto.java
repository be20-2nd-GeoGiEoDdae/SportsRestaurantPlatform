package com.ohgiraffers.geogieoddae.auth.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithdrawRequestDto {
    private String withdrawReason;  // 탈퇴 사유 (선택사항)
}
