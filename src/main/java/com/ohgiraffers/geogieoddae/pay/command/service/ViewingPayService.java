package com.ohgiraffers.geogieoddae.pay.command.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.ohgiraffers.geogieoddae.pay.command.dto.ViewingPayInfoRequest;
import com.ohgiraffers.geogieoddae.pay.command.dto.ViewingPayInfoResponse;
import com.ohgiraffers.geogieoddae.pay.command.entity.ViewingPayEntity;
import com.ohgiraffers.geogieoddae.pay.command.repository.UserRepository;
import com.ohgiraffers.geogieoddae.pay.command.repository.ViewingPayRepository;
import com.ohgiraffers.geogieoddae.pay.command.repository.ViewingRepository;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ViewingPayService {
  @Value("${toss-widget-example-secret-key}")
  private String SECRET_KEY;
  private final ViewingPayRepository viewingPayRepository;
  private final UserRepository userRepository;
  private final ViewingRepository viewingRepository;

  public ViewingPayInfoResponse viewingPayPut(ViewingPayInfoRequest request,Long viewingPayId){
    String orderId =  viewingPayId+"_"+UUID.randomUUID().toString().substring(0, 20);
    ViewingPayEntity viewingPayEntity = ViewingPayEntity.builder()
        .viewingPayPrice(request.getViewingPayPrice())
            .member(userRepository.getReferenceById(viewingPayId))
                .viewing(viewingRepository.getReferenceById(request.getViewingCode()))
        .build();
        //memberCode와 viewingCode 추가 필요 code값 받아서 엔티티 연관 관계 저장
            //결제id저장 필요?
    //viewing에서 결제금액 뽑아와야됨
    ViewingPayInfoResponse response = new ViewingPayInfoResponse(
        request.getViewingCode(),
        request.getViewingPayPrice(),
        orderId,
        request.getViewingPayPrice()
    );
    viewingPayRepository.save(viewingPayEntity);
    return response;
  }
  public ResponseEntity<String> viewingPayConfirm(String paymentKey,String orderId,Long amount){//10분안에 결제 승인 되야함
    try {
      //DB에서 저장된 주문 정보 조회
       ViewingPayEntity viewingPay=viewingPayRepository.findById((long)17).orElseThrow();//

      //금액 검증
/*      if (!viewingPay.getViewingPayPrice().equals(amount)) {
        System.out.println("디비 "+viewingPay.getViewingPayPrice());
        System.out.println("결제 "+amount);
        throw new IllegalStateException("금액이 일치하지 않습니다");
      }//viewingPay.getViewingPayPrice()Long타입으로 변환 필요  OrderId는 Integer타입*/

      //토스페이먼츠 결제 승인 API 호출
      HttpHeaders headers = new HttpHeaders();
      String authorization = Base64.getEncoder()
          .encodeToString((SECRET_KEY + ":").getBytes());
      headers.set("Authorization", "Basic " + authorization);
      headers.setContentType(MediaType.APPLICATION_JSON);

      Map<String, Object> payloadMap = new HashMap<>();
      payloadMap.put("paymentKey", paymentKey);
      payloadMap.put("orderId", orderId);
      payloadMap.put("amount", amount);

      HttpEntity<Map<String, Object>> request =
          new HttpEntity<>(payloadMap, headers);

      RestTemplate restTemplate = new RestTemplate();

      ResponseEntity<JsonNode> response = restTemplate.postForEntity(
          "https://api.tosspayments.com/v1/payments/confirm",
          request,
          JsonNode.class
      );

      if (response.getStatusCode() == HttpStatus.OK) {
        JsonNode successNode = response.getBody();

        return ResponseEntity.ok("결제 성공");
      } else {
        return ResponseEntity.status(response.getStatusCode())
            .body("결제 승인 실패");
      }

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("결제 처리 중 오류 발생: " + e.getMessage());
    }
  }
}
