package com.ohgiraffers.geogieoddae.admin.query.dto;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UserDto {
  private Long userCode;
  private String userName;
  private String userEmail;
  private String userPhoneNumber;
  private String userAddress;
  private String userRole;

  // 사업자 필드 추가
  private Long entrepreneurCode;
  private Integer entrepreneurId;
  private String entrepreneurStatus;

  public static UserDto fromEntity(UserEntity u) {
    return new UserDto(
      u.getUserCode(),
      u.getUserName(),
      u.getUserEmail(),
      u.getUserPhoneNumber(),
      u.getUserAddress(),
      u.getUserRole() == null ? null : u.getUserRole().name(),
      null,
      null,
      null
    );
  }
}