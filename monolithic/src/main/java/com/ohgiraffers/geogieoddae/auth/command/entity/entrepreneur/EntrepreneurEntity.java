package com.ohgiraffers.geogieoddae.auth.command.entity.entrepreneur;

import java.util.List;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.pay.command.entity.EntrepreneurSubscribePaymentEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "entrepreneur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntrepreneurEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entrepreneur_code")
    private Long entrepreneurCode;

    @Column(name = "entrepreneur_id", nullable = false)
    private Integer entrepreneurId;

    @Column(name = "entrepreneur_certificate_url", nullable = false)
    private String entrepreneurCertificateUrl;                          // 사업자등록증 url

    @Column(name = "entrepreneur_bank_account", nullable = false)
    private Integer entrepreneurBankAccount;                            // 사업자 계좌번호

    // ENUM 타입 필드 선언
    @Enumerated(EnumType.STRING)
    private EntrepreneurStatus entrepreneurStatus;                      // 사업자 활성화상태

    @ManyToOne
    @JoinColumn(name = "user_code", nullable = false)
    private UserEntity member;

    // ✅ 관계 설정
    @OneToMany(mappedBy = "entrepreneur")
    private List<EntrepreneurSubscribePaymentEntity> payments;
}
