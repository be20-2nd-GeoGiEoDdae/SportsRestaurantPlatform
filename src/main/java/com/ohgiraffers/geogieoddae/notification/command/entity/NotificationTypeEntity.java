package com.ohgiraffers.geogieoddae.notification.command.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "notification_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationTypeEntity {

    @Id
    @Column(name = "notification_type_code")
    private Long notificationTypeCode;

    @Column(name = "notification_type_type", nullable = false)
    private String notificationTypeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type_status", columnDefinition = "ENUM('Y','N') DEFAULT 'Y'")
    private NotificationStatus notificationTypeStatus;

    @OneToMany(mappedBy = "notificationType")
    private List<NotificationEntity> notifications;
}
