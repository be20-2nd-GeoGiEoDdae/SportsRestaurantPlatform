package com.ohgiraffers.geogieoddae.auth.command.service;

import com.ohgiraffers.geogieoddae.notification.command.event.NotificationCreatedEvent;
import com.ohgiraffers.geogieoddae.pay.command.entity.ViewingPayStatus;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.ohgiraffers.geogieoddae.auth.command.dto.EntrepreneurRegisterRequestDto;
import com.ohgiraffers.geogieoddae.auth.command.entity.entrepreneur.EntrepreneurEntity;
import com.ohgiraffers.geogieoddae.auth.command.entity.entrepreneur.EntrepreneurStatus;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.auth.command.repository.EntrepreneurRepository;
import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntrepreneurService {

        
    private final EntrepreneurRepository entrepreneurRepository;
    private final UserRepository userRepository;   // 회원정보 조회용
    private final ApplicationEventPublisher publisher;

    @Transactional
    public void register(EntrepreneurRegisterRequestDto request) {

      Long userId  = request.getUserCode();
      Long notificationTypeCode = (long)1;
      publisher.publishEvent(new NotificationCreatedEvent(userId,notificationTypeCode) );//삭제 필요

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
