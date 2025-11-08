package com.geogieoddae.userservice.auth.command.service;

import org.springframework.stereotype.Service;

import com.geogieoddae.userservice.auth.command.dto.EntrepreneurRegisterRequestDto;
import com.geogieoddae.userservice.auth.command.entity.entrepreneur.EntrepreneurEntity;
import com.geogieoddae.userservice.auth.command.entity.entrepreneur.EntrepreneurStatus;
import com.geogieoddae.userservice.auth.command.entity.user.UserEntity;
import com.geogieoddae.userservice.auth.command.repository.EntrepreneurRepository;
import com.geogieoddae.userservice.auth.command.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntrepreneurService {

        
    private final EntrepreneurRepository entrepreneurRepository;
    private final UserRepository userRepository;   // 회원정보 조회용

    @Transactional
    public void register(EntrepreneurRegisterRequestDto request) {

        System.out.println("==== 사업자 등록 서비스 시작 ====");
        UserEntity user = userRepository.findById(request.getUserCode())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        EntrepreneurEntity entrepreneur = new EntrepreneurEntity();

        // 필드매핑
        entrepreneur.setEntrepreneurId(request.getEntrepreneurId());
        entrepreneur.setEntrepreneurCertificateUrl(request.getEntrepreneurCertificateUrl());
        entrepreneur.setEntrepreneurBankAccount(request.getEntrepreneurBankAccount());
        entrepreneur.setEntrepreneurStatus(EntrepreneurStatus.WAITING);
        entrepreneur.setMember(user);                   // 회원정보 매핑
        entrepreneurRepository.save(entrepreneur);

        System.out.println("entrepreneurId: " + entrepreneur.getEntrepreneurId());
        System.out.println("entrepreneurCertificateUrl: " + entrepreneur.getEntrepreneurCertificateUrl());
        System.out.println("entrepreneurBankAccount: " + entrepreneur.getEntrepreneurBankAccount());
        System.out.println("entrepreneurStatus: " + entrepreneur.getEntrepreneurStatus());
        System.out.println("member: " + entrepreneur.getMember());

        System.out.println("Saved : " + request.toString());
        
    }

}
