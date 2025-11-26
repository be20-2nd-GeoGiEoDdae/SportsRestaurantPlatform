package com.ohgiraffers.geogieoddae.admin.query.dto;

import com.ohgiraffers.geogieoddae.auth.command.entity.entrepreneur.EntrepreneurEntity;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CombinedEntrepreneurDto {
  private UserDto user;
  private EntrepreneurDto entrepreneur;

  public static CombinedEntrepreneurDto of(UserEntity u, EntrepreneurEntity e) {
    return new CombinedEntrepreneurDto(UserDto.fromEntity(u), EntrepreneurDto.fromEntity(e));
  }
}