package com.geogieoddae.mainservice.notification.query.controller;

import com.geogieoddae.mainservice.global.common.dto.ApiResponse;
import com.geogieoddae.mainservice.notification.query.dto.NotificationQueryResponse;
import com.geogieoddae.mainservice.notification.query.dto.NotificationTypeQueryResponse;
import com.geogieoddae.mainservice.notification.query.service.NotificationQueryService;
import com.geogieoddae.mainservice.notification.query.service.NotificationTypeQueryService;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationQueryController {
  private final NotificationTypeQueryService notificationTypeQueryService;
  private final NotificationQueryService notificationQueryService;

  @GetMapping("/{userCode}")
  public ResponseEntity<ApiResponse<List<NotificationQueryResponse>>> findByUser(
      @PathVariable Long userCode
  ) {
    return ResponseEntity.ok(ApiResponse.success( notificationQueryService.notificationSelectByUserCode(userCode)));
  }


  @GetMapping("/notificationType/search")
  public ResponseEntity<ApiResponse<List<NotificationTypeQueryResponse>>> findAll(

  ) {
    return ResponseEntity.ok(ApiResponse.success( notificationTypeQueryService.notificationTypeSelectAll()));
  }


  //@PreAuthorize("hasRole('USER')")자동 prefix 규칙을 따라 ROLE_USER을 찾음
  //@PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/notificationType/search/{notificationTypeCode}")
  public ResponseEntity<ApiResponse<NotificationTypeQueryResponse>> findByCode(
      @PathVariable Long notificationTypeCode
  ) {
    if( notificationTypeQueryService.notificationTypeSelectByCode(notificationTypeCode)==null){
      return ResponseEntity.ok(ApiResponse.failure( "500","존재하지않는 값"));
    }
    else{
      return ResponseEntity.ok(ApiResponse.success( notificationTypeQueryService.notificationTypeSelectByCode(notificationTypeCode)));
    }
  }

  //@PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/notificationType/search/type")//알림 이름 조회
  public ResponseEntity<ApiResponse<List<NotificationTypeQueryResponse>>> findByType(
      @RequestParam String notificationTypeType
  ) {
    return ResponseEntity.ok(ApiResponse.success( notificationTypeQueryService.notificationTypeSelectByType(notificationTypeType)));
  }
}
