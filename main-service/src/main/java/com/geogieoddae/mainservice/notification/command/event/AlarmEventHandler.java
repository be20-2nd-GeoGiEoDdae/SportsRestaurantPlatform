package com.geogieoddae.mainservice.notification.command.event;

import com.geogieoddae.mainservice.client.UserFeignClient;
import com.geogieoddae.mainservice.client.UserInfoResponse;
import com.geogieoddae.mainservice.notification.command.entity.NotificationEntity;
import com.geogieoddae.mainservice.notification.command.entity.NotificationStatus;
import com.geogieoddae.mainservice.notification.command.entity.NotificationTypeEntity;
import com.geogieoddae.mainservice.notification.command.repository.NotificationRepository;
import com.geogieoddae.mainservice.notification.command.repository.NotificationTypeRepository;
import com.geogieoddae.mainservice.notification.command.service.AlarmSseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.event.TransactionPhase;

@Component
@RequiredArgsConstructor
@Transactional
public class AlarmEventHandler {

    private final AlarmSseService sse;
    private final NotificationTypeRepository notificationTypeRepository;
    private final NotificationRepository notificationRepository;
    private final UserFeignClient userFeignClient;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onCreated(AlarmCreatedEvent e) {
        try {
            System.out.println("알림타입 코드 상태 : " + notificationTypeRepository.findById(e.notificationTypeCode()).orElseThrow().getNotificationTypeStatus());
            if (notificationTypeRepository.findById(e.notificationTypeCode()).orElseThrow().getNotificationTypeStatus() == NotificationStatus.Y) {
                String message = notificationSave(e);
                sse.send(e.userId(), message);
                System.out.println("알림발송:" + message);
            }
        } catch (Exception exception) {
            // 전송 실패해도 DB 저장은 이미 완료됨. 필요하면 재시도/아웃박스 설계 고려
            System.out.println(exception.getMessage());
        }
    }

    public String notificationSave(AlarmCreatedEvent e) {
        UserInfoResponse user = userFeignClient.getUserById(e.userId());

        NotificationTypeEntity notificationType = notificationTypeRepository.findById(e.notificationTypeCode()).orElseThrow();
        NotificationEntity notification = NotificationEntity.builder()
                .memberCode(user.getUserCode())
                .notificationType(notificationType)
                .build();
        notificationRepository.save(notification);
        return user.getUserName() + "님" + notificationType.getNotificationContent();
    }
}
