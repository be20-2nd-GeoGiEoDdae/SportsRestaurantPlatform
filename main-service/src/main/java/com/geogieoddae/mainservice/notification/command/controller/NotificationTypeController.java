package com.geogieoddae.mainservice.notification.command.controller;

import com.geogieoddae.mainservice.global.common.dto.ApiResponse;
import com.geogieoddae.mainservice.notification.command.dto.NotificationTypeRequest;
import com.geogieoddae.mainservice.notification.command.service.NotificationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificationType")
@RequiredArgsConstructor
public class NotificationTypeController {
  private final NotificationTypeService notificationTypeService;
  //알림 타입 생성
  //@PreAuthorize("hasRole('ADMIN')")
  @PostMapping("")
  public ResponseEntity<ApiResponse<String>> notificationTypeSave(
      @RequestBody NotificationTypeRequest notificationTypeRequest
  ) {
    return ResponseEntity.ok(
        ApiResponse.success(notificationTypeService.notificationTypeSave(notificationTypeRequest)));
  }

  //알림타입 삭제
  //@PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("{notificationTypeCode}")
  public ResponseEntity<ApiResponse<String>> notificationTypeDelete(
      @PathVariable Long notificationTypeCode
  ) {
    return ResponseEntity.ok(
        ApiResponse.success(notificationTypeService.notificationTypeDeleteById(notificationTypeCode)));
  }
  //알림 타입 수정
  //@PreAuthorize("hasRole('ADMIN')")
  @PatchMapping("{notificationTypeCode}")
  public ResponseEntity<ApiResponse<String>> notificationTypeUpdate(
      @PathVariable Long notificationTypeCode
      ,@RequestBody NotificationTypeRequest request
  ){
    return ResponseEntity.ok(
        ApiResponse.success(notificationTypeService.notificationTypePatchById(notificationTypeCode,request)));
  }
}
