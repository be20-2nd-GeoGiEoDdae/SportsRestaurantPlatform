package com.ohgiraffers.geogieoddae.pay.command.service;


import com.ohgiraffers.geogieoddae.auth.command.repository.EntrepreneurRepository;
import com.ohgiraffers.geogieoddae.pay.command.dto.EntrepreneurSubscribeChargeRequest;
import com.ohgiraffers.geogieoddae.pay.command.dto.EntrepreneurSubscribePaymentRequest;
import com.ohgiraffers.geogieoddae.pay.command.entity.EntrepreneurSubscribePaymentEntity;
import com.ohgiraffers.geogieoddae.pay.command.repository.EntrepreneurSubscribePaymentRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor

public class EntrepreneurSubscribePaymentService {

  private final RestTemplate rest = new RestTemplate();
  private final EntrepreneurSubscribePaymentRepository entrepreneurSubscribePaymentRepository;
  private final EntrepreneurRepository entrepreneurRepository;
  private final long thirtyDays = 1000L * 60 * 60 * 24 * 30;

  @Value("${toss-api-key}")
  private String SECRET_KEY;

  @Transactional
  public Map<String,Object> billingKeyGet(String authKey,String customerKey){
    try {
      // Basic 인증 헤더 생성
      String encodedAuth = Base64.getEncoder()
          .encodeToString((SECRET_KEY + ":").getBytes());
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

      int set=customerKey.indexOf('_');
      if(set>=0){
        Long entrepreneurCode=Long.parseLong(customerKey.substring(0,set));
        subscribePaymentRequest.setEntrepreneurId(Long.parseLong(customerKey.substring(0,set)));
        System.out.println("ID값 : "+Long.parseLong(customerKey.substring(0,set)));
        subscribePaymentRequest.setEntrepreneurSubscribeAmount((10000));
        subscribePaymentRequest.setEntrepreneurSubscribeBillingkey(
            (String) responseBody.get("billingKey"));
        subscribePaymentRequest.setEntrepreneurSubscribeCustomerKey(customerKey);
        subscribePaymentRequest.setEntrepreneurSubscribeOrderId( entrepreneurCode+"_"+ UUID.randomUUID());
      }
      System.out.println("빌링키 : " + responseBody.get("billingKey"));

      subscribePaymentSave(subscribePaymentRequest);

      System.out.println(response.getBody());
      return response.getBody();

    } catch (Exception e) {
      Map<String, Object> error = new HashMap<>();
      error.put("error", e.getMessage());
      return error;
    }
  }


@Transactional
  public Map<String, Object> subscribePaymentCharge(
      Long userId) {//유저아이디가 아닌 사업자 구별번호일듯
    EntrepreneurSubscribePaymentEntity entrepreneurSubscribePayment=entrepreneurSubscribePaymentRepository.findById(userId).orElseThrow();
    String billingKey = entrepreneurSubscribePayment.getEntrepreneurSubscribeBillingkey();
    String customerKey =entrepreneurSubscribePayment.getEntrepreneurSubscribeCustomerKey();
    Integer amount = entrepreneurSubscribePayment.getEntrepreneurSubscribePayment();
    String orderId = entrepreneurSubscribePayment.getEntrepreneur().getEntrepreneurId()+"_"+UUID.randomUUID();//결제 생성시 마다 주문아이디 생성

    HttpHeaders headers = new HttpHeaders();
    headers.setBasicAuth(SECRET_KEY, "");               // Authorization: Basic base64(secretKey:)
    headers.setContentType(MediaType.APPLICATION_JSON);

    Map<String, Object> body = new HashMap<>();
    body.put("customerKey", customerKey);//db에 저장된 유저 커스텀 키로 수정
    body.put("amount",amount);
    body.put("orderId", orderId);
    body.put("orderName", "구독 결제");

    Map<String, Object> toss = rest.postForObject(
        "https://api.tosspayments.com/v1/billing/" + billingKey,
        new HttpEntity<>(body, headers),
        Map.class
    );
    entrepreneurSubscribePayment.setEntrepreneurSubscribeOrderId(orderId);
    return toss;

  }

  public void subscribePaymentSave(

      EntrepreneurSubscribePaymentRequest request) {
    EntrepreneurSubscribePaymentEntity subscribePaymentEntity = EntrepreneurSubscribePaymentEntity.builder()
        .entrepreneurCode(request.getEntrepreneurId())
        .entrepreneurSubscribeEndAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")).plusDays(30))
        .entrepreneurSubscribePayment(request.getEntrepreneurSubscribeAmount())
        .entrepreneurSubscribeBillingkey(request.getEntrepreneurSubscribeBillingkey())
        .entrepreneurSubscribeCustomerKey(request.getEntrepreneurSubscribeCustomerKey())
        .entrepreneur(entrepreneurRepository.findById(request.getEntrepreneurId()).orElseThrow())
        .build();
    entrepreneurSubscribePaymentRepository.save(subscribePaymentEntity);
  }

  @Transactional
  public String deleteBillingKey(Long userId) {//유저아이디가 아닌 사업자 구별번호일듯

    String billingKey = entrepreneurSubscribePaymentRepository.findById(userId).get()
        .getEntrepreneurSubscribeBillingkey();
    String url = "https://api.tosspayments.com/v1/billing/"
        + billingKey;//https://api.tosspayments.com/v1/billing/{billingKey} url형태 설정

    HttpHeaders headers = new HttpHeaders();
    headers.setBasicAuth(SECRET_KEY, "");

    HttpEntity<Void> httpEntity = new HttpEntity<>(headers);
    try {
        rest.exchange(url, HttpMethod.DELETE, httpEntity, Void.class);//성공시 db삭제
        entrepreneurSubscribePaymentRepository.deleteById(userId);
        return "삭제 성공";
    }catch (Exception e) {
    }
    return null;
  }

  public ResponseEntity<String> subscribePaymentSelectByOrderId(Long entrepreneurCode) {
    String orderId=entrepreneurSubscribePaymentRepository.findById(entrepreneurCode).orElseThrow().getEntrepreneurSubscribeOrderId();
    String url = "https://api.tosspayments.com/v1/payments/orders/"+orderId;
    String encodedAuth = Base64.getEncoder()
        .encodeToString((SECRET_KEY + ":").getBytes());
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Basic " + encodedAuth);

    HttpEntity<Map<String,Object>> request=new HttpEntity<>(headers);

    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<String> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        request,
        String.class
    );
    return response;
  }
  public ResponseEntity<String> subscribePaymentLogAll(LocalDateTime startDate,LocalDateTime endDate) {
    String url = "https://api.tosspayments.com/v1/transactions"+
        "?startDate="+startDate+"&endDate="+endDate;
       // + "?startDate=2025-10-25T00:00:00"
        //+ "&endDate=2025-11-03T23:59:59";

    String encodedAuth = Base64.getEncoder()
        .encodeToString((SECRET_KEY + ":").getBytes());
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Basic " + encodedAuth);

    HttpEntity<Void> entity = new HttpEntity<>(headers);
    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<String> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        entity,
        String.class
    );

    return ResponseEntity.status(response.getStatusCode())
        .body(response.getBody());
  }
}
