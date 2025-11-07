package com.ohgiraffers.geogieoddae.pay.command.controller;

import com.ohgiraffers.geogieoddae.pay.command.entity.ViewingPayEntity;
import com.ohgiraffers.geogieoddae.pay.command.repository.ViewingPayRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "관람 결제 페이지 api")
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/viewingPay")
public class ViewHtmlController {
  private final ViewingPayRepository viewingPayRepository;

  @Value("${toss-widget-example-client-key}")
  private String widgetExampleKey;

  @GetMapping("/viewingPage")
  public String viewingPayPage() {
    return "forward:/viewing-pay.html";
  }

  @GetMapping("/pay/success")
  public String success(@RequestParam String paymentKey,
      @RequestParam String orderId,
      @RequestParam Long amount,
      Model model) {
    model.addAttribute("paymentKey", paymentKey);
    model.addAttribute("orderId", orderId);
    model.addAttribute("amount", amount);
    return "pay/success.html";
  }
  @GetMapping("/index/{orderId}")
  public String subscribePage(@PathVariable String orderId, Model model) {
    ViewingPayEntity viewingPay =viewingPayRepository.findByViewingPayOrderId(orderId);
    model.addAttribute("customerKey", viewingPay.getViewingPayCustomerKey());
    model.addAttribute("orderId",viewingPay.getViewingPayOrderId());
    model.addAttribute("amount",viewingPay.getViewingPayPrice());
    model.addAttribute("clientKey",widgetExampleKey);
    return "check";
  }
}
