package com.ohgiraffers.geogieoddae.admin.query.service;

import com.ohgiraffers.geogieoddae.admin.query.dto.CombinedEntrepreneurDto;
import com.ohgiraffers.geogieoddae.admin.query.dto.EntrepreneurUpdateRequest;
import com.ohgiraffers.geogieoddae.auth.command.entity.entrepreneur.EntrepreneurEntity;
import com.ohgiraffers.geogieoddae.auth.command.entity.entrepreneur.EntrepreneurStatus;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.auth.command.repository.EntrepreneurRepository;
import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminEntrepreneurService {

  private final UserRepository userRepository;
  private final EntrepreneurRepository entrepreneurRepository;

  public CombinedEntrepreneurDto getCombinedByUserCode(Long userCode) {
    UserEntity user = userRepository.findById(userCode)
        .orElseThrow(() -> new EntityNotFoundException("user not found"));

    EntrepreneurEntity ent = entrepreneurRepository.findByMember_UserCode(userCode);

    return CombinedEntrepreneurDto.of(user, ent);
  }

  public void updateEntrepreneur(Long entrepreneurCode, EntrepreneurUpdateRequest req) {
    EntrepreneurEntity ent = entrepreneurRepository.findById(entrepreneurCode)
        .orElseThrow(() -> new EntityNotFoundException("entrepreneur not found"));

    if (req.getEntrepreneurCertificateUrl() != null) {
      ent.setEntrepreneurCertificateUrl(req.getEntrepreneurCertificateUrl());
    }

    if (req.getEntrepreneurBankAccount() != null) {
      // 엔티티가 Integer이면 숫자만 추출후 parse, 권장: 엔티티 타입을 String으로 변경
      String digits = req.getEntrepreneurBankAccount().replaceAll("[^0-9]", "");
      try {
        ent.setEntrepreneurBankAccount(Integer.parseInt(digits));
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("invalid bank account format");
      }
    }

    if (req.getEntrepreneurRegNumber() != null) {
      // 엔티티에 필드가 없으면 이 부분은 주석 처리하거나 실제 필드명으로 수정하세요
      // ent.setEntrepreneurRegNumber(req.getEntrepreneurRegNumber());
    }

    if (req.getEntrepreneurStatus() != null) {
      try {
        ent.setEntrepreneurStatus(EntrepreneurStatus.valueOf(req.getEntrepreneurStatus()));
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("invalid entrepreneur status");
      }
    }

    entrepreneurRepository.save(ent);
  }
}
