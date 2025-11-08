package com.geogieoddae.mainservice.pay.query.controller;

import com.geogieoddae.mainservice.global.common.dto.ApiResponse;
import com.geogieoddae.mainservice.pay.query.dto.PaySearchResponse;
import java.util.List;

import com.geogieoddae.mainservice.pay.query.service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/viewingPay/search")
@RequiredArgsConstructor
public class PayController {
  private final PayService payService;
  @GetMapping("/{payCode}")
  public ResponseEntity<ApiResponse<PaySearchResponse>> selectByPayCode(@PathVariable Long payCode){

    return ResponseEntity.ok(ApiResponse.success(payService.selectPay(payCode)));
  }
  @GetMapping("/viewing/{viewingCode}")
  public ResponseEntity<ApiResponse<List<PaySearchResponse>>> selectByViewingCode(@PathVariable Long viewingCode){

    return ResponseEntity.ok(ApiResponse.success(payService.selectPayByViewingCode(viewingCode)));
  }

  @GetMapping("")
  public ResponseEntity<ApiResponse<List<PaySearchResponse>>> selectAll(){

    return ResponseEntity.ok(ApiResponse.success(payService.selectPayAll()));
  }
  @GetMapping("/member/{userCode}")
  public ResponseEntity<ApiResponse<List<PaySearchResponse>>> selectByUserCode(Long userCode){

    return ResponseEntity.ok(ApiResponse.success(payService.selectPayByUserCode(userCode)));
  }
}
