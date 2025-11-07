package com.ohgiraffers.geogieoddae.notification.query.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.global.jwt.JwtTokenProvider;
import com.ohgiraffers.geogieoddae.notification.query.dto.NotificationQueryResponse;
import com.ohgiraffers.geogieoddae.notification.query.service.NotificationQueryService;
import com.ohgiraffers.geogieoddae.notification.query.service.NotificationTypeQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "알림 조회  api")
@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationQueryController {
  private final NotificationQueryService notificationQueryService;

  @GetMapping("/{userCode}")
  public ResponseEntity<ApiResponse<List<NotificationQueryResponse>>> findByUser(
      @PathVariable Long userCode
  ) {
    return ResponseEntity.ok(ApiResponse.success( notificationQueryService.notificationSelectByUserCode(userCode)));
  }

}
