package com.ohgiraffers.geogieoddae.pay.command.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;
import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.notification.command.event.NotificationCreatedEvent;
import com.ohgiraffers.geogieoddae.pay.command.dto.ViewingPayRequest;
import com.ohgiraffers.geogieoddae.pay.command.entity.ViewingPayEntity;
import com.ohgiraffers.geogieoddae.pay.command.entity.ViewingPayStatus;
import com.ohgiraffers.geogieoddae.pay.command.repository.ViewingPayRepository;
import com.ohgiraffers.geogieoddae.viewing.command.entity.ViewingEntity;
import com.ohgiraffers.geogieoddae.viewing.command.entity.ViewingUserEntity;
import com.ohgiraffers.geogieoddae.viewing.command.repository.ViewingRepository;
import com.ohgiraffers.geogieoddae.viewing.command.repository.ViewingUserRepository;
import jakarta.transaction.Transactional;
import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ViewingPayService {

  private final ViewingUserRepository viewingUserRepository;
  @Value("${toss.widget.example.secret.key}")
  private String SECRET_KEY;
  private final ViewingPayRepository viewingPayRepository;
  private final UserRepository userRepository;
  private final ViewingRepository viewingRepository;
  private final ApplicationEventPublisher publisher;


  public String viewingSave(ViewingPayRequest request){
    String orderId = UUID.randomUUID().toString();
    //String viewingCustomerKey =UUID.randomUUID().toString();
    UserEntity user=userRepository.findById(request.getUserCode()).orElseThrow();
    String customerKey=userRepository.findById(request.getUserCode()).orElseThrow().getCustomerKey();


    ViewingPayEntity viewingPayEntity = ViewingPayEntity.builder()
        .viewingPayPrice(request.getViewingPayPrice())
        //.viewingPayCustomerKey(viewingCustomerKey)
        .viewingPayOrderId(orderId)
        .viewingPayStatus(ViewingPayStatus.waiting)
        .member(userRepository.getReferenceById(request.getUserCode()))
        .viewing(viewingRepository.getReferenceById(request.getViewingCode()))
        .build();
    viewingPayRepository.save(viewingPayEntity);
    return orderId;
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

        ViewingUserEntity viewingUser = ViewingUserEntity.builder()
            .viewingUserDeposit(amount.intValue())
            .viewingUserIsAttend(false)
            .viewing(viewingPay.getViewing())
            .member(viewingPay.getMember())
            .build();

        viewingUserRepository.save(viewingUser);






        Long userId  = viewingPay.getMember().getUserCode();
        Long notificationTypeCode = (long)1;
        publisher.publishEvent(new NotificationCreatedEvent(userId,notificationTypeCode) );
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
  //특정 시각에 환불ㄹ
  //사업자가 상태변경 시 환불
  @Transactional
  public String viewingPayCancel(Long viewingPayCode){
    try{
    ViewingPayEntity viewingPay=viewingPayRepository.findById(viewingPayCode).orElseThrow();
    ViewingEntity viewing=viewingRepository.findById(viewingPay.getViewing().getViewingCode()).orElseThrow();
    String paymentKey=viewingPay.getViewingPayPaymentKey();
    Long payAmount=viewingPay.getViewingPayPrice();
    if(LocalDateTime.now().minusDays(7).isBefore(viewing.getViewingAt())){
      payAmount=payAmount* 60L / 100L  ;
    }
    //Long payAmount=viewingPay.getViewingPayPrice();
    String url = "https://api.tosspayments.com/v1/payments/"+paymentKey+"/cancel";
    String cancelReason= "유저 변심으로 인환 환불";
    String encodedAuth = Base64.getEncoder()
        .encodeToString((SECRET_KEY + ":").getBytes());
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Basic " + encodedAuth);

    Map<String, Object> payloadMap = new HashMap<>();
    payloadMap.put("cancelReason", cancelReason);
    payloadMap.put("cancelAmount", payAmount);

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
      System.out.println("관람 취소 금"+paymentKey);
  return "관람 결제 취소 성공";
    }catch(Exception e){
      return "관람 결제 취소 실패";
    }
  }

  @Transactional
  public String viewingPayCancelUser(Long viewingCode,Long userCode){
    try{
      ViewingPayEntity viewingPay= viewingPayRepository
          .findByViewing_ViewingCodeAndMember_UserCode(viewingCode, userCode)
          .orElseThrow(() -> new RuntimeException("데이터 없음"));
      ViewingEntity viewing=viewingRepository.findById(viewingCode).orElseThrow();
      ViewingUserEntity viewingUser=viewingUserRepository.findByViewing_ViewingCodeAndMember_UserCode(viewingCode, userCode);
      viewingUser.setViewingUserIsAttend(true);//임시
      String paymentKey=viewingPay.getViewingPayPaymentKey();
      Long payAmount=viewingPay.getViewingPayPrice();
      if(LocalDateTime.now().minusDays(7).isBefore(viewing.getViewingAt())){
        payAmount=payAmount* 60L / 100L  ;
      }
      //Long payAmount=viewingPay.getViewingPayPrice();
      String url = "https://api.tosspayments.com/v1/payments/"+paymentKey+"/cancel";
      String cancelReason= "유저 변심으로 인환 환불";
      String encodedAuth = Base64.getEncoder()
          .encodeToString((SECRET_KEY + ":").getBytes());
      HttpHeaders headers = new HttpHeaders();
      headers.set("Authorization", "Basic " + encodedAuth);

      Map<String, Object> payloadMap = new HashMap<>();
      payloadMap.put("cancelReason", cancelReason);
      payloadMap.put("cancelAmount", payAmount);

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
      System.out.println("관람 취소 금"+paymentKey);
      return "관람 결제 취소 성공";
    }catch(Exception e){
      return "관람 결제 취소 실패";
    }
  }

}
