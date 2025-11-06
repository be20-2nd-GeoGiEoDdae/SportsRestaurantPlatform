package com.ohgiraffers.geogieoddae.pay.command.controller;

import com.ohgiraffers.geogieoddae.pay.command.repository.ViewingPayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/viewingPay")
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
  @GetMapping("/index/{viewingPayCode}")
  public String subscribePage(@PathVariable Long viewingPayCode, Model model) {
    model.addAttribute("customerKey", viewingPayRepository.findById(viewingPayCode).get().getViewingPayCustomerKey());
    model.addAttribute("orderId",viewingPayRepository.findById(viewingPayCode).get().getViewingPayOrderId());
    model.addAttribute("amount",viewingPayRepository.findById(viewingPayCode).get().getViewingPayPrice());
    model.addAttribute("clientKey",widgetExampleKey);
    return "check";
  }
}
