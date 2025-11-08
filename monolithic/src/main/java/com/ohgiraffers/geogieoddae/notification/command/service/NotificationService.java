package com.ohgiraffers.geogieoddae.notification.command.service;

import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;
import com.ohgiraffers.geogieoddae.notification.command.dto.NotificationRequest;
import com.ohgiraffers.geogieoddae.notification.command.entity.NotificationEntity;
import com.ohgiraffers.geogieoddae.notification.command.event.NotificationCreatedEvent;
import com.ohgiraffers.geogieoddae.notification.command.repository.NotificationRepository;
import com.ohgiraffers.geogieoddae.notification.command.repository.NotificationTypeRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class NotificationService {
  private final NotificationRepository notificationRepository;
  private final ApplicationEventPublisher publisher;

  @Transactional
  public String notificationSave(NotificationRequest request) {
    publisher.publishEvent(new NotificationCreatedEvent(request.getMemberCode(), request.getNotificationTypeCode()));

    return "저장 완료";
  }
  //조회된 알림 상태 수정
  @Transactional
  public void notificationChecked(Long userCode){
    List<NotificationEntity> userNotificationList= notificationRepository.findByMember_UserCode(userCode);
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
