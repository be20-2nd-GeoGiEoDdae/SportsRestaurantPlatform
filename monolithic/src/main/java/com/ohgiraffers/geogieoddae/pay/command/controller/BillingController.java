package com.ohgiraffers.geogieoddae.pay.command.controller;


import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.pay.command.service.EntrepreneurSubscribePaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.*;
import java.util.Map;


@Tag(name = "구독 결제 api")
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/subscribe")
public class BillingController {

  @Value("${toss.client.key}")
  private String clientKey;
  // Base64가 시크릿 키를 암호화하거나 보호하지 않지만, HTTP 헤더로 안전하게 텍스트로 전송
  private final EntrepreneurSubscribePaymentService entrepreneurSubscribePaymentService;


/*  @PreAuthorize("hasAuthority('ROLE_ENTREPRENEUR')")
  @GetMapping("/request/{entrepreneurCode}")//엔터프라이즈 커스터머 키 생성
  public ResponseEntity<ApiResponse<Map<String,String>>> subscribePage(@PathVariable Long entrepreneurCode) {
    return ResponseEntity.ok(ApiResponse.success(entrepreneurSubscribePaymentService.request(entrepreneurCode)));
  }*/


  @GetMapping("/index/{customerKey}")
  public String subscribePage(@PathVariable String customerKey, Model model) {
    model.addAttribute("clientKey",clientKey );
    model.addAttribute("customerKey",customerKey);
    return "index";
  }

  // 빌링키 발급
  @GetMapping("/billingKey/success")
  public String success(@RequestParam String authKey,
      @RequestParam String customerKey
  ) {
    ApiResponse<String> result =
        ApiResponse.success(
            entrepreneurSubscribePaymentService.billingKeyGet(authKey, customerKey)
        );
    if (result.isSuccess()) {
      // 결제 성공 → 성공 페이지로 리다이렉트 (Vue 라우트)
      return "redirect:http://localhost:5173/MyPage/Subscribe/Success";
    } else {
      return "실패";
    }
  }

  // 빌링키 발급 실패
  @GetMapping("/fail")
  public String fail(@RequestParam String message,
      @RequestParam String code,
      Model model) {
    model.addAttribute("message", message);
    model.addAttribute("code", code);
    return "fail";
  }

  //빌링키로 결제 요청시 자동 승인
//  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @PostMapping("/charge/{billingKey}")
  public ResponseEntity<ApiResponse<String>> charge(@PathVariable String billingKey) {
    //Map<String,Object> toss =
      String status= entrepreneurSubscribePaymentService.subscribePaymentCharge(billingKey);

    return ResponseEntity.ok(ApiResponse.success(status));
  }

  //빌링키 삭제
  //@PreAuthorize("hasAuthority('ROLE_ENTREPRENEUR')")
  @DeleteMapping("/charge/{entrepreneurCode}")
  public ResponseEntity<ApiResponse<String>> deleteBillingKey(@PathVariable Long entrepreneurCode) {
    return ResponseEntity.ok(
        ApiResponse.success(entrepreneurSubscribePaymentService.deleteBillingKey(entrepreneurCode)));
  }

  //구독 결제검색
  @GetMapping("/{entrepreneurCode}")
  public ResponseEntity<String> tossSelect(
      @PathVariable Long entrepreneurCode
  ){
    return entrepreneurSubscribePaymentService.subscribePaymentSelectByOrderId(entrepreneurCode);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/log")//회원들 구독결제 로그 전체
  public ResponseEntity<String> getSubscribeLog(
      @RequestParam LocalDateTime startDate,
      @RequestParam LocalDateTime endDate
  ) {
      return entrepreneurSubscribePaymentService.subscribePaymentLogAll(startDate,endDate);
  }

}