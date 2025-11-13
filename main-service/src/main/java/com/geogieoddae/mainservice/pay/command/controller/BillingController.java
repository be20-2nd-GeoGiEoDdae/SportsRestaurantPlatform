package com.geogieoddae.mainservice.pay.command.controller;

import com.geogieoddae.mainservice.global.common.dto.ApiResponse;
import com.geogieoddae.mainservice.pay.command.service.EntrepreneurSubscribePaymentService;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.http.*;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/subscribe")
public class BillingController {

  @Value("${toss.client.key}")
  private String clientKey;
  // Base64가 시크릿 키를 암호화하거나 보호하지 않지만, HTTP 헤더로 안전하게 텍스트로 전송
  private final EntrepreneurSubscribePaymentService entrepreneurSubscribePaymentService;


  @GetMapping("/index/{entrepreneurCode}")
  public String subscribePage(@PathVariable Long entrepreneurCode, Model model) {
    model.addAttribute("clientKey",clientKey );
    model.addAttribute("customerKey", entrepreneurCode+"_"+UUID.randomUUID());
  //  model.addAttribute("userName", entrepreneurRepository.findById(entrepreneurCode).orElseThrow().getMember().getUserName());
 //   model.addAttribute("userEmail",entrepreneurRepository.findById(entrepreneurCode).orElseThrow().getMember().getUserName());

    return "index";
  }

  // 빌링키 발급
  @GetMapping("/billingKey/success")
  @ResponseBody//ResponseEntity로 반환?
  public Map<String, Object> success(@RequestParam String authKey,
      @RequestParam String customerKey
      //  @RequestBody Long entrepreneurId//?
  ) {
    return entrepreneurSubscribePaymentService.billingKeyGet(authKey,customerKey);
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

  //빌링키로 결제
  //@PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/charge/{userCode}")
  public ResponseEntity<ApiResponse<Map<String, Object>>> charge(@PathVariable Long userCode) {
    Map<String,Object> toss =entrepreneurSubscribePaymentService.subscribePaymentCharge(userCode);

    return ResponseEntity.ok(ApiResponse.success(toss));
  }

  //빌링키 삭제
  @DeleteMapping("/charge/{userCode}")
  public ResponseEntity<ApiResponse<String>> deleteBillingKey(@PathVariable Long userCode) {
    return ResponseEntity.ok(
        ApiResponse.success(entrepreneurSubscribePaymentService.deleteBillingKey(userCode)));
  }

  //구독 결제검색
  @GetMapping("/{entrepreneurCode}")
  public ResponseEntity<String> tossSelect(
      @PathVariable Long entrepreneurCode
  ){
    return entrepreneurSubscribePaymentService.subscribePaymentSelectByOrderId(entrepreneurCode);
  }

  //@PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/log")
  public ResponseEntity<String> getSubscribeLog(
      @RequestParam LocalDateTime startDate,
      @RequestParam LocalDateTime endDate
  ) {
      return entrepreneurSubscribePaymentService.subscribePaymentLogAll(startDate,endDate);
  }

}