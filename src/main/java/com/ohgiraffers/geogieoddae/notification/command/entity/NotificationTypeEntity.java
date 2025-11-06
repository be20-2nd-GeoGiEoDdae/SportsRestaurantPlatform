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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_type_code")
    private Long notificationTypeCode;

    @Column(name = "notification_type_type", nullable = false)
    private String notificationTypeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type_status")
    private NotificationStatus notificationTypeStatus;

    @Column(name = "notification_content", nullable = false)
    private String notificationContent;

    @OneToMany(mappedBy = "notificationType")
    private List<NotificationEntity> notifications;

    public void deleteNotificationType()
    {
      this.notificationTypeStatus=NotificationStatus.N;
    }
}
