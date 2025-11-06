package com.ohgiraffers.geogieoddae.pay.command.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.pay.command.dto.ViewingPayRequest;
import com.ohgiraffers.geogieoddae.pay.command.service.ViewingPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/viewingPay")
public class ViewingPayController {

  private final ViewingPayService viewingPayService;

  @GetMapping("/success")
  public ResponseEntity<ApiResponse<String>> confirmPayment(
      @RequestParam String paymentKey,
      @RequestParam String orderId,
      @RequestParam Long amount) {
    return viewingPayService.viewingPayConfirm(paymentKey, orderId, amount);
  }
  @PostMapping("/info")//결제할 관람 정보  저장
  public String direct(      @RequestBody ViewingPayRequest request){
    Long viewingPayCode=viewingPayService.viewingSave(request);
    return "redirect:/viewingPay/index/"+viewingPayCode;
  }


  @PostMapping("/cancel/{viewingPayId}")//관람 금액 환불
  public ResponseEntity<String> cancel(
      @PathVariable Long viewingPayId
  ){
    return viewingPayService.viewingPayCancel(viewingPayId);
  }


}
