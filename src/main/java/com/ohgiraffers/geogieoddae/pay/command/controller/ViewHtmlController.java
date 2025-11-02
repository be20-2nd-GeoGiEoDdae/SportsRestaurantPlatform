package com.ohgiraffers.geogieoddae.pay.command.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewHtmlController {
  @GetMapping("/viewing-pay")
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
}
