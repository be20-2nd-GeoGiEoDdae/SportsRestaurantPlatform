package com.ohgiraffers.geogieoddae.notification.command.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.notification.command.dto.NotificationRequest;
import com.ohgiraffers.geogieoddae.notification.command.service.AlarmSseService;
import com.ohgiraffers.geogieoddae.notification.command.service.NotificationService;
import com.ohgiraffers.geogieoddae.notification.command.service.NotificationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {

  private final AlarmSseService alarmSseService;
  private final NotificationTypeService notificationTypeService;
  private final NotificationService notificationService;

  //알림 연결
  @GetMapping(value = "/connections/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public SseEmitter subscribe(@PathVariable Long id,
      @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
    return alarmSseService.sseSubscribe(id,
        lastEventId);
  }
  //알림 연결 해제
  @DeleteMapping("/disconnection/{userId}")
  public ResponseEntity<?> disconnectByUser(@PathVariable Long userId) {
    int count = alarmSseService.disconnectByUserId(userId);
    return ResponseEntity.ok().body("{\"disconnected\":" + count + "}");
  }
  //관리자 알림 직접 생성
  //@PreAuthorize("hasRole('ADMIN')")
  @PostMapping("")
  public ResponseEntity<ApiResponse<String>> notificationSave(@RequestBody NotificationRequest request) {
    return ResponseEntity.ok(ApiResponse.success(notificationService.notificationSave(request)));
  }
  //본인 알림 삭제
  @DeleteMapping("/{notificationCode}")
  public ResponseEntity<ApiResponse<String>> notificationSave(@PathVariable Long notificationCode) {
    return ResponseEntity.ok(ApiResponse.success(notificationService.notificationDeleteByCode(notificationCode)));
  }
}
