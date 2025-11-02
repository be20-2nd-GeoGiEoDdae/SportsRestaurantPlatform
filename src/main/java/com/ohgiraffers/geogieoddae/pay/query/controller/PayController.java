package com.ohgiraffers.geogieoddae.pay.query.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.pay.query.dto.PaySearchResponse;
import com.ohgiraffers.geogieoddae.pay.query.service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay/search")
@RequiredArgsConstructor
public class PayController {
  private final PayService payService;
  @GetMapping("/{payCode}")
  public ResponseEntity<ApiResponse<PaySearchResponse>> search(@PathVariable Long payCode){

    return ResponseEntity.ok(ApiResponse.success(payService.selectPay(payCode)));
  }
}
