package com.ohgiraffers.geogieoddae.auth.command.entity.entrepreneur;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.pay.command.entity.EntrepreneurSubscribePaymentEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "entrepreneur")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntrepreneurEntity {

    @Id
    @Column(name = "entrepreneur_code")
    private Long entrepreneurCode;

    @Column(name = "entrepreneur_id", nullable = false)
    private Integer entrepreneurId;

    @ManyToOne
    @JoinColumn(name = "user_code", nullable = false)
    private UserEntity member;

    // ✅ 관계 설정
    @OneToMany(mappedBy = "entrepreneur")
    private List<EntrepreneurSubscribePaymentEntity> payments;
}
