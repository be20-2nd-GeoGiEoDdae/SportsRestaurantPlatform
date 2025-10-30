package com.ohgiraffers.geogieoddae.admin.command.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.ohgiraffers.geogieoddae.announcement.command.entity.AnnouncementEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "admin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_code")
    private Long adminCode;

    @Column(name = "admin_id", nullable = false)
    private String adminId;

    @Column(name = "admin_password", nullable = false)
    private String adminPassword;

    @Column(name = "admin_refresh_token", nullable = false)
    private String adminRefreshToken;

    @Column(name = "admin_refresh_token_expires_at", nullable = false)
    private LocalDateTime adminRefreshTokenExpiresAt;

    // ✅ 관계 설정
    @OneToMany(mappedBy = "admin")
    private List<AnnouncementEntity> announcements;
}
