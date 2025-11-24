package com.ohgiraffers.geogieoddae.viewing.command.entity;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "viewing_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewingUserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viewing_user_code")
    private Long viewingUserCode;

    @Column(name = "viewing_user_deposit")
    private Integer viewingUserDeposit;

    @Column(name = "viewing_user_isattend", nullable = false)
    private Boolean viewingUserIsAttend;

    @ManyToOne
    @JoinColumn(name = "viewing_code", nullable = false)
    private ViewingEntity viewing;

    @ManyToOne
    @JoinColumn(name = "user_code", nullable = false)
    private UserEntity member;
}
