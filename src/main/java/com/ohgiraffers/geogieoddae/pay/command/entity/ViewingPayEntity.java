package com.ohgiraffers.geogieoddae.pay.command.entity;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.global.common.entity.BaseTimeEntity;
import com.ohgiraffers.geogieoddae.viewing.command.entity.ViewingEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "viewing_pay")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewingPayEntity extends BaseTimeEntity {

    @Id
    @Column(name = "viewing_pay_code")
    private Long viewingPayCode;

    @Column(name = "viewing_pay_price", nullable = false)
    private Integer viewingPayPrice;

    @Column(name = "viewing_pay_refund_date", nullable = false)
    private LocalDateTime viewingPayRefundDate;


    @ManyToOne
    @JoinColumn(name = "user_code", nullable = false)
    private UserEntity member;

    @ManyToOne
    @JoinColumn(name = "viewing_code", nullable = false)
    private ViewingEntity viewing;
}
