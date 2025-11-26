package com.ohgiraffers.geogieoddae.pay.query.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.pay.query.dto.BillingQueryDto;
import com.ohgiraffers.geogieoddae.pay.query.dto.BillingQueryFindUserResponse;
import com.ohgiraffers.geogieoddae.pay.query.service.BillingQueryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscribe")
@RequiredArgsConstructor
public class BillingQueryController {
  private final BillingQueryService billingQueryService;

  /*@PreAuthorize("hasRole")*/
  @GetMapping("")
  public ResponseEntity<ApiResponse<List<BillingQueryDto>>> findAll() {
    return ResponseEntity.ok(ApiResponse.success(billingQueryService.findAll()));
  }
  @GetMapping("search/{userCode}")
  public  ResponseEntity<ApiResponse<Page<BillingQueryFindUserResponse>>>  findByUserCode(@PathVariable Long userCode , Pageable pageable) {
    Page<BillingQueryFindUserResponse> page =billingQueryService.findByUserCode(userCode,pageable);
    return ResponseEntity.ok(ApiResponse.success(page));
  }

}
