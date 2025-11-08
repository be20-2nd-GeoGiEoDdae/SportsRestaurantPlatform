package com.geogieoddae.mainservice.notification.command.service;

import com.geogieoddae.mainservice.notification.command.dto.NotificationRequest;
import com.geogieoddae.mainservice.notification.command.entity.NotificationEntity;
import com.geogieoddae.mainservice.notification.command.event.AlarmCreatedEvent;
import com.geogieoddae.mainservice.notification.command.repository.NotificationRepository;
import com.geogieoddae.mainservice.notification.command.repository.NotificationTypeRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
  private final NotificationRepository notificationRepository;
  private final NotificationTypeRepository notificationTypeRepository;
  private final AlarmSseService alarmSseService;
  private final ApplicationEventPublisher publisher;

  @Transactional
  public String notificationSave(NotificationRequest request) {
    publisher.publishEvent(new AlarmCreatedEvent(request.getMemberCode(), request.getNotificationTypeCode()));

    return "저장 완료";
  }
  //조회된 알림 상태 수정
  @Transactional
  public void notificationChecked(Long userCode){
    List<NotificationEntity> userNotificationList= notificationRepository.findByMemberCode(userCode);
    userNotificationList.forEach(notificationEntity -> {if (notificationEntity.getNotificationChecked()==false)
      notificationEntity.setNotificationChecked(true);
    });
  }

  public String notificationDeleteByCode(Long notificationCode) {
    String message;
    try {
        notificationRepository.deleteById(notificationCode);
        message = "알림 제거 완료";
    }catch (Exception e){
      message=e.getMessage();
    }
    return message;
  }
}
