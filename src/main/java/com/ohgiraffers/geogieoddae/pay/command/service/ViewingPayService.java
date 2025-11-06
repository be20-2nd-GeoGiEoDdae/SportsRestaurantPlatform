package com.ohgiraffers.geogieoddae.pay.command.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;
import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.notification.command.event.AlarmCreatedEvent;
import com.ohgiraffers.geogieoddae.notification.command.service.AlarmSseService;
import com.ohgiraffers.geogieoddae.notification.command.service.NotificationService;
import com.ohgiraffers.geogieoddae.pay.command.dto.ViewingPayRequest;
import com.ohgiraffers.geogieoddae.pay.command.entity.ViewingPayEntity;
import com.ohgiraffers.geogieoddae.pay.command.entity.ViewingPayStatus;
import com.ohgiraffers.geogieoddae.pay.command.repository.ViewingPayRepository;
import com.ohgiraffers.geogieoddae.viewing.command.repository.ViewingRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.notification.NotificationPublisher;
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
  private final ApplicationEventPublisher publisher;


  public Long viewingSave(ViewingPayRequest request){
    String orderId = request.getUserCode()+"_"+UUID.randomUUID().toString().substring(0, 20);
    String viewingCustomerKey =request.getViewingCode()+"_"+ UUID.randomUUID().toString().substring(0, 20);

    ViewingPayEntity viewingPayEntity = ViewingPayEntity.builder()
        .viewingPayPrice(request.getViewingPayPrice())
        .viewingPayCustomerKey(viewingCustomerKey)
        .viewingPayOrderId(orderId)
        .viewingPayStatus(ViewingPayStatus.waiting)
        .member(userRepository.getReferenceById(request.getUserCode()))
        .viewing(viewingRepository.getReferenceById(request.getViewingCode()))
        .build();
    return viewingPayRepository.save(viewingPayEntity).getViewingPayCode();
  }

  @Transactional
  public ResponseEntity<ApiResponse<String>> viewingPayConfirm(String paymentKey,String orderId,Long amount){//10분안에 결제 승인 되야함
    try {
      //DB에서 저장된 주문 정보 조회
       ViewingPayEntity viewingPay=viewingPayRepository.findByViewingPayOrderId(orderId);//중간에 오더 아이디 바뀔시 에러 발생

      //금액 검증
      if (viewingPay.getViewingPayPrice()==null||!viewingPay.getViewingPayPrice().equals(amount)) {
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("결제 처리 중 오류 발생: 가격 불일치 ");
      }
      //viewingPay.getViewingPayPrice()Long타입으로 변환 필요  OrderId는 Integer타입

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
        Long userId  = viewingPay.getMember().getUserCode();
        Long notificationTypeCode = (long)1;
        publisher.publishEvent(new AlarmCreatedEvent(userId,notificationTypeCode) );
        viewingPay.setViewingPayPaymentKey(paymentKey);
        viewingPay.setViewingPayStatus(ViewingPayStatus.approve);

        return ResponseEntity.ok(ApiResponse.success("결제 성공"));

      } else {
        return ResponseEntity.ok(ApiResponse.failure(response.getStatusCode().toString(), "결제 실패"));
      }
    } catch (Exception e) {
      return ResponseEntity.ok(ApiResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "결제시 오류 발생 : "+e.getMessage()));
    }
  }
  @Transactional
  public ResponseEntity<String> viewingPayCancel(Long viewingPayCode){
    ViewingPayEntity viewingPay=viewingPayRepository.findById(viewingPayCode).orElseThrow();
    String paymentKey=viewingPay.getViewingPayPaymentKey();
    String url = "https://api.tosspayments.com/v1/payments/"+paymentKey+"/cancel";
    String cancelReason= "관람 참석으로 인환 환불";
    String encodedAuth = Base64.getEncoder()
        .encodeToString((SECRET_KEY + ":").getBytes());
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Basic " + encodedAuth);

    Map<String, Object> payloadMap = new HashMap<>();
    payloadMap.put("cancelReason", cancelReason);

    HttpEntity<Map<String, Object>> request =
        new HttpEntity<>(payloadMap, headers);
    RestTemplate restTemplate = new RestTemplate();

    viewingPay.setViewingPayStatus(ViewingPayStatus.cancel);
    viewingPay.setViewingPayRefundDate(LocalDateTime.now());

    ResponseEntity<String> response = restTemplate.exchange(
        url,
        HttpMethod.POST,
        request,
        String.class
    );
  return response;
  }

}
