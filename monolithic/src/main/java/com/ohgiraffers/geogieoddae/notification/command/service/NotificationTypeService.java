package com.ohgiraffers.geogieoddae.notification.command.service;


import com.ohgiraffers.geogieoddae.notification.command.dto.NotificationTypeRequest;
import com.ohgiraffers.geogieoddae.notification.command.entity.NotificationStatus;
import com.ohgiraffers.geogieoddae.notification.command.entity.NotificationTypeEntity;
import com.ohgiraffers.geogieoddae.notification.command.repository.NotificationTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class NotificationTypeService {
  private final NotificationTypeRepository notificationTypeRepository;
  public String notificationTypeSave(NotificationTypeRequest request){
    NotificationTypeEntity notificationType = new NotificationTypeEntity();
      notificationType.setNotificationTypeType(request.getNotificationTypeType());
      notificationType.setNotificationTypeStatus(request.getNotificationTypeStatus());
      notificationType.setNotificationContent(request.getNotificationContent());
      notificationTypeRepository.save(notificationType);
    return "저장 완료";
  }
  @Transactional
  public String notificationTypeDeleteById(Long notificationTypeCode){
    NotificationTypeEntity notificationType=notificationTypeRepository.findById(notificationTypeCode).orElseThrow();
    if(notificationType.getNotificationTypeStatus()== NotificationStatus.N){
      return "이미 삭제된 알림 타입 입니다";
    }else{
      //notificationType.deleteNotificationType();
      notificationType.setNotificationTypeStatus(NotificationStatus.N);
      return "삭제 완료";
    }
  }
  public String notificationTypePatchById(Long notificationTypeCode, NotificationTypeRequest request){
    NotificationTypeEntity notificationType=notificationTypeRepository.findById(notificationTypeCode).orElseThrow();
    if(request.getNotificationTypeType()!=null){notificationType.setNotificationTypeType(request.getNotificationTypeType());}
    if(request.getNotificationContent()!=null){notificationType.setNotificationContent(request.getNotificationContent());}
    notificationTypeRepository.save((notificationType));
    return "패치 성공";
  }
}