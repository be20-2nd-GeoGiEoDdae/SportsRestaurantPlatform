package com.geogieoddae.userservice.admin.command.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Column(name = "admin_refresh_token", nullable = true)
    private String adminRefreshToken;

    @Column(name = "admin_refresh_token_expires_at", nullable = true)
    private LocalDateTime adminRefreshTokenExpiresAt;

//
}