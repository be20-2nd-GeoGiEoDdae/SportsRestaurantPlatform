package com.ohgiraffers.geogieoddae.admin.query.dto;

import com.ohgiraffers.geogieoddae.auth.command.entity.entrepreneur.EntrepreneurEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class EntrepreneurDto {
  private Long entrepreneurCode;
  private Integer entrepreneurId;         // 현재 엔티티에 있으면 포함
  private String entrepreneurCertificateUrl;
  private String entrepreneurBankAccount; // 프론트는 String
  private String entrepreneurRegNumber;   // 사업자등록번호 (엔티티필드명 확인)
  private String entrepreneurStatus;      // WAITING/APPROVED/REJECTED

  public static EntrepreneurDto fromEntity(EntrepreneurEntity e) {
    if (e == null) return null;
    return EntrepreneurDto.builder()
      .entrepreneurCode(e.getEntrepreneurCode())
      .entrepreneurId(e.getEntrepreneurId())
      .entrepreneurCertificateUrl(e.getEntrepreneurCertificateUrl())
      .entrepreneurBankAccount(e.getEntrepreneurBankAccount() == null ? null : String.valueOf(e.getEntrepreneurBankAccount()))
      .entrepreneurRegNumber(null) // 엔티티에 해당 필드명 있으면 map
      .entrepreneurStatus(e.getEntrepreneurStatus() == null ? null : e.getEntrepreneurStatus().name())
      .build();
  }
}