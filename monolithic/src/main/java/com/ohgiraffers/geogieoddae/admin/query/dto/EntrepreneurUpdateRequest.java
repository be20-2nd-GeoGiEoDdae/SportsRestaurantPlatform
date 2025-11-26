package com.ohgiraffers.geogieoddae.admin.query.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 관리자 화면에서 사업자 정보를 수정할 때 사용하는 요청 DTO
 * 모든 필드는 선택적(optional)이며 컨트롤러에서 @Valid로 검증할 수 있음.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntrepreneurUpdateRequest {

  @Size(max = 512, message = "certificateUrl은 최대 512자입니다")
  private String entrepreneurCertificateUrl;

  // 숫자 및 하이픈만 허용 (예: 123-456-7890)
  @Pattern(regexp = "[0-9-]*", message = "계좌번호는 숫자와 '-'만 허용합니다")
  private String entrepreneurBankAccount;

  // 사업자등록번호 형식(숫자 및 하이픈 허용)
  @Pattern(regexp = "[0-9-]*", message = "사업자등록번호는 숫자와 '-'만 허용합니다")
  private String entrepreneurRegNumber;

  // 상태 문자열은 'WAITING', 'APPROVED', 'REJECTED' 중 하나여야 함.
  @Size(max = 20, message = "상태 길이가 너무 깁니다")
  private String entrepreneurStatus;

}
