package com.geogieoddae.mainservice.notification.query.service;

import com.geogieoddae.mainservice.notification.query.dto.NotificationTypeQueryResponse;
import com.geogieoddae.mainservice.notification.query.mapper.NotificationTypeMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationTypeQueryService {
  private final NotificationTypeMapper notificationTypeMapper;

  public List<NotificationTypeQueryResponse> notificationTypeSelectAll() {
    return notificationTypeMapper.notificationTypeSelectAll();
  }

  public NotificationTypeQueryResponse notificationTypeSelectByCode(Long notificationTypeCode) {
    return notificationTypeMapper.notificationTypeSelectByCode(notificationTypeCode);//결과값 널이면 예외 처리

  }

  public List<NotificationTypeQueryResponse>  notificationTypeSelectByType(String notificationTypeType) {
    return notificationTypeMapper.notificationTypeSelectByType(notificationTypeType);//결과값 널이면 예외 처리

  }
}
