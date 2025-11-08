package com.geogieoddae.mainservice.notification.query.service;

import com.geogieoddae.mainservice.notification.command.service.NotificationService;
import com.geogieoddae.mainservice.notification.query.dto.NotificationQueryResponse;
import com.geogieoddae.mainservice.notification.query.mapper.NotificationMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationQueryService {
  private final NotificationMapper notificationMapper;
  private final NotificationService notificationService;
  public List<NotificationQueryResponse> notificationSelectByUserCode(Long userCode) {
    List<NotificationQueryResponse> notificationQueryResponseList =notificationMapper.notificationSelectByUserCode(userCode);
    notificationService.notificationChecked(userCode);
    return notificationQueryResponseList;
  }
}
