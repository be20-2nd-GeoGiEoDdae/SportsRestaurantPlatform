package com.geogieoddae.mainservice.notification.command.entity;

import com.geogieoddae.mainservice.global.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

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


    @Column(name = "notification_checked")//, columnDefinition = "BOOLEAN DEFAULT FALSE")
    @Builder.Default
    private Boolean notificationChecked=false;

    private Long memberCode;

    @ManyToOne
    @JoinColumn(name = "notification_type_code", nullable = false)
    private NotificationTypeEntity notificationType;
}
