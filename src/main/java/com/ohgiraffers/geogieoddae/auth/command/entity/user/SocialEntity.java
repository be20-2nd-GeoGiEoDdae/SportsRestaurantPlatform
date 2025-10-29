package com.ohgiraffers.geogieoddae.auth.command.entity.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "social")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialEntity {

    @Id
    @Column(name = "social_code")
    private Long socialCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = false, columnDefinition = "ENUM('카카오','구글','네이버')")
    private SocialType socialType;

    @Column(name = "social_id", nullable = false)
    private String socialId;

    @ManyToOne
    @JoinColumn(name = "user_code", nullable = false)
    private UserEntity member;
}
