package com.ohgiraffers.geogieoddae.auth.command.entity.user;

import java.time.LocalDateTime;
import java.util.List;

import com.ohgiraffers.geogieoddae.auth.command.entity.entrepreneur.EntrepreneurEntity;
import com.ohgiraffers.geogieoddae.global.common.entity.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity extends BaseTimeEntity {

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_code")
        private Long userCode;

        @Column(name = "user_name", nullable = false)
        private String userName;

        @Column(name = "user_email", nullable = false)
        private String userEmail;

        @Column(name = "user_phone_number", nullable = false)
        private String userPhoneNumber;

        @Column(name = "user_address")
        private String userAddress;


        @Column(name = "user_refresh_token", nullable = true)
        private String userRefreshToken;

        @Column(name = "user_refresh_token_expires_at", nullable = true)
        private LocalDateTime userRefreshTokenExpiresAt;

        @Enumerated(EnumType.STRING)
        @Column(name = "user_role", columnDefinition = "ENUM('USER','ENTREPRENEUR')")  // UserRole 에 맞게 영어로 수정 - 성현
        private UserRole userRole;

        // 예시: UserEntity.java
        @Column(nullable = true)
        private String kakaoAccessToken;                    // 카카오 억세스토큰

        @Column(nullable = true)
        private String kakaoRefreshToken;                   // 카카오 리프레쉬토큰

        @Column(nullable = true)
        private LocalDateTime kakaoAccessTokenExpiresAt;    // 카카오 억세스토큰 만료

        @Column(nullable = true)
        private LocalDateTime kakaoRefreshTokenExpiresAt;   // 카카오 리프레쉬토큰 만료

        @Column(nullable = true)
        private String customerKey;

        // 회원 탈퇴 관련 필드
        @Builder.Default
        @Column(name = "is_withdrawn", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
        private Boolean isWithdrawn = false;                    // 탈퇴 여부 (기본값: false)

        @Column(name = "withdrawn_at")
        private LocalDateTime withdrawnAt;                      // 탈퇴 일시
        
        @Column(name = "withdrawn_reason")
        private String withdrawReason;                          // 탈퇴 사유 (선택사항)

        // ✅ 관계 매핑
        @OneToMany(mappedBy = "member")
        private List<SocialEntity> socials;

        @OneToMany(mappedBy = "member")
        private List<EntrepreneurEntity> entrepreneurs;
}
