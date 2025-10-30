package com.ohgiraffers.geogieoddae.admin.command.entity;

import com.ohgiraffers.geogieoddae.announcement.command.entity.AnnouncementEntity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

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

    // ✅ 관계 설정
    @OneToMany(mappedBy = "admin")
    private List<AnnouncementEntity> announcements;
}
