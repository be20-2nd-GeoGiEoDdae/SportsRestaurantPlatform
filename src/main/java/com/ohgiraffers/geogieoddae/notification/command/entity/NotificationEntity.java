package com.ohgiraffers.geogieoddae.notification.command.entity;

import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_code")
    private Long notificationCode;

    @Column(name = "notification_checked", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean notificationChecked;

  @Column(name = "notification_content", nullable = false)
  private String notificationContent;

    @ManyToOne
    @JoinColumn(name = "Key2", nullable = false)
    private UserEntity member;

    @ManyToOne
    @JoinColumn(name = "notification_type_code", nullable = false)
    private NotificationTypeEntity notificationType;
}
