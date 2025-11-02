package com.ohgiraffers.geogieoddae.pay.command.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.pay.command.dto.EntrepreneurSubscribeChargeRequest;
import com.ohgiraffers.geogieoddae.pay.command.repository.EntrepreneurSubscribePaymentRepository;
import com.ohgiraffers.geogieoddae.pay.command.repository.UserRepository;
import com.ohgiraffers.geogieoddae.pay.command.service.EntrepreneurSubscribePaymentService;
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

  @Value("${toss-client-key}")
  private String clientKey;
  // Base64가 시크릿 키를 암호화하거나 보호하지 않지만, HTTP 헤더로 안전하게 텍스트로 전송
  private final EntrepreneurSubscribePaymentService entrepreneurSubscribePaymentService;
  private final EntrepreneurSubscribePaymentRepository subscribePaymentRepository;
  private final UserRepository userRepository;

  @GetMapping("/index/{userCode}")
  public String subscribePage(@PathVariable Long userCode, Model model) {
    model.addAttribute("clientKey",clientKey );
    model.addAttribute("customerKey", userCode+"_"+UUID.randomUUID());
    model.addAttribute("userName",userRepository.findById(userCode).orElseThrow().getUserName());
    model.addAttribute("userEmail",userRepository.findById(userCode).orElseThrow().getUserEmail());

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
  @PostMapping("/charge/{userCode}")
  public ResponseEntity<ApiResponse<Map<String, Object>>> charge(@RequestBody EntrepreneurSubscribeChargeRequest req ,@PathVariable long userCode) {
    Map<String,Object> toss =entrepreneurSubscribePaymentService.subscribeCharge(req,userCode);

    return ResponseEntity.ok(ApiResponse.success(toss));
  }

  //빌링키 삭제
  @DeleteMapping("/charge/{userCode}")
  public ResponseEntity<ApiResponse<Void>> deleteBillingKey(@PathVariable long userCode) {
    return ResponseEntity.ok(
        ApiResponse.success(entrepreneurSubscribePaymentService.deleteBillingKey(userCode)));
  }


}