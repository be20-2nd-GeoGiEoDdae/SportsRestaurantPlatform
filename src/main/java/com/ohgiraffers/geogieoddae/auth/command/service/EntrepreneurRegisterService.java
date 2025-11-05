package com.ohgiraffers.geogieoddae.auth.command.service;

import com.ohgiraffers.geogieoddae.auth.command.entity.entrepreneur.EntrepreneurEntity;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.auth.command.repository.EntrepreneurRepository;
import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntrepreneurRegisterService {

    private final EntrepreneurRepository entrepreneurRepository;
    private final UserRepository userRepository;   // 회원정보 조회용

    // 사업자 등록 신청
    public void registerEntrepreneur(
            Integer entrepreneurId,
            String entrepreneurCertificateUrl,
            Integer entrepreneurBankAccount,
            Long userCode
    ) {
        UserEntity user = userRepository.findById(userCode).orElseThrow(
                ()->new IllegalArgumentException("존재하지 않는 회원입니다.")
        );

        EntrepreneurEntity entrepreneur = EntrepreneurEntity.builder()
                .entrepreneurId(entrepreneurId)
                .entrepreneurCertificateUrl(entrepreneurCertificateUrl)
                .entrepreneurBankAccount(entrepreneurBankAccount)
                .member(user)
                .build();

        System.out.println("entrepreneur: " + entrepreneur.toString() + "");

        entrepreneurRepository.save(entrepreneur);

        System.out.println("저장 여부 : " + entrepreneurRepository.save(entrepreneur));
    }

    public void registerEntrepreneur(String request) {
    }
}
