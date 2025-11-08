package com.geogieoddae.userservice.auth.command.entity.entrepreneur;

import com.geogieoddae.userservice.auth.command.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

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

}
