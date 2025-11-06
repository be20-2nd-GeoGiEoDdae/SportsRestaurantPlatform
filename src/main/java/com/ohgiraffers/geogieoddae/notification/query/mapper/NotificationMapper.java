package com.ohgiraffers.geogieoddae.notification.query.mapper;

import com.ohgiraffers.geogieoddae.notification.query.dto.NotificationQueryResponse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NotificationMapper {

  List<NotificationQueryResponse> notificationSelectByUserCode(Long userCode);
}
