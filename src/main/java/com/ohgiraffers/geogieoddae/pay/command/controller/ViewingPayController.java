package com.ohgiraffers.geogieoddae.pay.command.controller;

import com.ohgiraffers.geogieoddae.pay.command.dto.ViewingPayInfoRequest;
import com.ohgiraffers.geogieoddae.pay.command.dto.ViewingPayInfoResponse;
import com.ohgiraffers.geogieoddae.pay.command.service.ViewingPayService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ViewingPayController {

  @Value("${toss-widget-example-client-key}")
  private String widgetExampleKey;

  private final ViewingPayService viewingPayService;

  private final String customerKey="m1KWTWMsrKvuOgrlMh8BX";//결제별 구별 키

  @PostMapping("/config/{viewingPayId}")
  public ResponseEntity<Map<String,Object>> config(
      @RequestBody ViewingPayInfoRequest viewingPayInfoRequest,
      @PathVariable Long viewingPayId
  ){
    Map<String,Object> responseMap=new HashMap<>();
    ViewingPayInfoResponse response =viewingPayService.viewingPayPut(viewingPayInfoRequest,viewingPayId);

    responseMap.put("orderId",response.getOrderId());
    responseMap.put("customerKey",customerKey);
    responseMap.put("clientKey",widgetExampleKey);
    responseMap.put("amount",response.getDeposit());
    return ResponseEntity.ok(responseMap);
  }
  @GetMapping("/viewing/success")
  public ResponseEntity<String> confirmPayment(
      @RequestParam String paymentKey,
      @RequestParam String orderId,
      @RequestParam Long amount) {
    return viewingPayService.viewingPayConfirm(paymentKey, orderId, amount);
  }
}
