package com.ohgiraffers.geogieoddae.pay.query.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.pay.query.dto.PaySearchResponse;
import com.ohgiraffers.geogieoddae.pay.query.service.PayService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "결제 조회 api ")
@RestController
@RequestMapping("/api/viewingPay/search")
@RequiredArgsConstructor
public class PayController {
  private final PayService payService;
  //결제 코드로 검색
  @GetMapping("/{payCode}")
  public ResponseEntity<ApiResponse<PaySearchResponse>> selectByPayCode(@PathVariable Long payCode){

    return ResponseEntity.ok(ApiResponse.success(payService.selectPay(payCode)));
  }
  //관람 코드로 검색
  @GetMapping("/viewing/{viewingCode}")
  public ResponseEntity<ApiResponse<List<PaySearchResponse>>> selectByViewingCode(@PathVariable Long viewingCode){

    return ResponseEntity.ok(ApiResponse.success(payService.selectPayByViewingCode(viewingCode)));
  }

  //전체 결제 기록 조회
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("")
  public ResponseEntity<ApiResponse<List<PaySearchResponse>>> selectAll(){

    return ResponseEntity.ok(ApiResponse.success(payService.selectPayAll()));
  }
  //유저 코드로 검색
  @GetMapping("/member/{userCode}")
  public ResponseEntity<ApiResponse<List<PaySearchResponse>>> selectByUserCode(Long userCode){

    return ResponseEntity.ok(ApiResponse.success(payService.selectPayByUserCode(userCode)));
  }
}
