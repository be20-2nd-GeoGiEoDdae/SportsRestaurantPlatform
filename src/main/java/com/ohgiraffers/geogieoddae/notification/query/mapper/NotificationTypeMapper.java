package com.ohgiraffers.geogieoddae.notification.query.mapper;

import com.ohgiraffers.geogieoddae.notification.query.dto.NotificationTypeQueryResponse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface NotificationTypeMapper {
  List<NotificationTypeQueryResponse> notificationTypeSelectAll();

  NotificationTypeQueryResponse notificationTypeSelectByCode(Long notificationTypeCode);

  List<NotificationTypeQueryResponse>  notificationTypeSelectByType(String notificationTypeType);
}
