package com.ohgiraffers.geogieoddae.pay.command.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.pay.command.dto.ViewingPayRequest;
import com.ohgiraffers.geogieoddae.pay.command.service.ViewingPayService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "관람 결제 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/viewingPay")
public class ViewingPayController {

  private final ViewingPayService viewingPayService;


  @PostMapping("/info")//결제할 관람 정보  저장
  public String direct(      @RequestBody ViewingPayRequest request){
    return  viewingPayService.viewingSave(request);//viewingPayServiceOrderId
  }

  //@PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/cancel/{viewingPayId}")//관람 금액 환불
  public ResponseEntity<ApiResponse<String>> cancel(
      @PathVariable Long viewingPayId
  ){
    return ResponseEntity.ok(ApiResponse.success(viewingPayService.viewingPayCancel(viewingPayId)));
  }
  @DeleteMapping("/cancel/{viewingCode}/{userCode}")//관람 금액 환불
  public ResponseEntity<ApiResponse<String>> cancel(
      @PathVariable Long viewingCode,
      @PathVariable Long userCode
  ){
    return ResponseEntity.ok(ApiResponse.success(viewingPayService.viewingPayCancelUser(viewingCode,userCode)));
  }


}
