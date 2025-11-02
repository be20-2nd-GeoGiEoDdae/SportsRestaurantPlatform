package com.ohgiraffers.geogieoddae.pay.command.service;


import com.ohgiraffers.geogieoddae.pay.command.dto.EntrepreneurSubscribeChargeRequest;
import com.ohgiraffers.geogieoddae.pay.command.dto.EntrepreneurSubscribePaymentRequest;
import com.ohgiraffers.geogieoddae.pay.command.entity.EntrepreneurSubscribePaymentEntity;
import com.ohgiraffers.geogieoddae.pay.command.repository.EntrepreneurSubscribePaymentRepository;
import com.ohgiraffers.geogieoddae.pay.command.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@EnableJpaAuditing

public class EntrepreneurSubscribePaymentService {

  private final RestTemplate rest = new RestTemplate();
  private final EntrepreneurSubscribePaymentRepository entrepreneurSubscribePaymentRepository;
  private final UserRepository userRepository;
  @Value("${toss-api-key}")
  private String secretKey;

  public Map<String,Object> billingKeyGet(String authKey,String customerKey){
    try {
      // Basic 인증 헤더 생성
      String encodedAuth = Base64.getEncoder()
          .encodeToString((secretKey + ":").getBytes());
      // HTTP 헤더 설정
      HttpHeaders headers = new HttpHeaders();
      headers.set("Authorization", "Basic " + encodedAuth);
      headers.setContentType(MediaType.APPLICATION_JSON);
      // 요청 본문 생성
      Map<String, String> requestBody = new HashMap<>();
      requestBody.put("authKey", authKey);
      requestBody.put("customerKey", customerKey);

      HttpEntity<Map<String, String>> entity =
          new HttpEntity<>(requestBody, headers);
      // API 호출
      System.out.println("apikey" + encodedAuth);

      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<Map> response = restTemplate.postForEntity(
          "https://api.tosspayments.com/v1/billing/authorizations/issue",
          entity,
          Map.class
      );

      Map<String, Object> responseBody = response.getBody();
      EntrepreneurSubscribePaymentRequest subscribePaymentRequest
          = new EntrepreneurSubscribePaymentRequest();
      subscribePaymentRequest.setEntrepreneurSubscribePayment((10000));
      subscribePaymentRequest.setEntrepreneurSubscribeBillingkey(
          (String) responseBody.get("billingKey"));

      System.out.println("빌링키 : " + responseBody.get("billingKey"));


    //subscribePaymentSave(subscribePaymentRequest);

      System.out.println(response.getBody());
      return response.getBody();

    } catch (Exception e) {
      Map<String, Object> error = new HashMap<>();
      error.put("error", e.getMessage());
      return error;
    }
  }

  @Transactional
  public void subscribePaymentSave(

      EntrepreneurSubscribePaymentRequest request) {
    EntrepreneurSubscribePaymentEntity subscribePaymentEntity = EntrepreneurSubscribePaymentEntity.builder()
        .entrepreneurSubscribePayment(request.getEntrepreneurSubscribePayment())
        .entrepreneurSubscribeBillingkey(request.getEntrepreneurSubscribeBillingkey())
        .entrepreneurCode(request.getEntrepreneurId())
        .build();
    entrepreneurSubscribePaymentRepository.save(subscribePaymentEntity);
  }


  public Map<String, Object> subscribeCharge(EntrepreneurSubscribeChargeRequest request,
      Long userId) {//유저아이디가 아닌 사업자 구별번호일듯
    String billingKey = entrepreneurSubscribePaymentRepository.findById(userId).get()
        .getEntrepreneurSubscribeBillingkey();

    HttpHeaders headers = new HttpHeaders();
    headers.setBasicAuth(secretKey, "");               // Authorization: Basic base64(secretKey:)
    headers.setContentType(MediaType.APPLICATION_JSON);

    Map<String, Object> body = new HashMap<>();
    body.put("customerKey", request.getCustomerKey());//db에 저장된 유저 커스텀 키로 수정
    body.put("amount",10000);
    body.put("orderId", java.util.UUID.randomUUID().toString());
    body.put("orderName", "구독 결제");
    if (request.getCustomerEmail() != null) {
      body.put("customerEmail",
          userRepository.findById(userId).get().getUserEmail());

    }
    if (request.getCustomerName() != null) {
      body.put("customerName",
          userRepository.findById(userId).get().getUserName());
    }

    Map<String, Object> toss = rest.postForObject(
        "https://api.tosspayments.com/v1/billing/" + billingKey,
        new HttpEntity<>(body, headers),
        Map.class
    );
    return toss;

  }

  public Void deleteBillingKey(long userId) {//유저아이디가 아닌 사업자 구별번호일듯

    String billingKey = entrepreneurSubscribePaymentRepository.findById(userId).get()
        .getEntrepreneurSubscribeBillingkey();
    String url = "https://api.tosspayments.com/v1/billing/"
        + billingKey;//https://api.tosspayments.com/v1/billing/{billingKey} url형태 설정

    HttpHeaders headers = new HttpHeaders();
    headers.setBasicAuth(secretKey, "");

    HttpEntity<Void> httpEntity = new HttpEntity<>(headers);
    return rest.exchange(
                url, HttpMethod.DELETE, httpEntity, Void.class)
            .getBody();
  }
}
