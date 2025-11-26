package com.ohgiraffers.geogieoddae.pay.command.service;


import com.ohgiraffers.geogieoddae.auth.command.entity.entrepreneur.EntrepreneurEntity;
import com.ohgiraffers.geogieoddae.auth.command.repository.EntrepreneurRepository;
import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;
import com.ohgiraffers.geogieoddae.pay.command.entity.EntrepreneurSubscribePaymentEntity;
import com.ohgiraffers.geogieoddae.pay.command.repository.EntrepreneurSubscribePaymentRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor

public class EntrepreneurSubscribePaymentService {

  private final RestTemplate rest = new RestTemplate();
  private final EntrepreneurSubscribePaymentRepository entrepreneurSubscribePaymentRepository;
  private final EntrepreneurRepository entrepreneurRepository;
  private final UserRepository userRepository;


  @Value("${toss.api.key}")
  private String SECRET_KEY;
  private final int SUBSCRIBE_PAY = 10000;

  @Transactional
  public String billingKeyGet(String authKey, String customerKey) {
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
      Map<String, Object> responseBody = BillingKeyCall(entity);



/*      EntrepreneurSubscribePaymentEntity entrepreneurSubscribePayment
          = entrepreneurSubscribePaymentRepository
          .findByEntrepreneurSubscribeCustomerKey(customerKey);*/
      System.out.println("검색 시 문제");
      EntrepreneurEntity entrepreneurEntity=entrepreneurRepository.findByMember_UserCode
          (userRepository.findByCustomerKey(customerKey).orElseThrow().getUserCode());

      System.out.println("빌링키 : " + responseBody.get("billingKey"));
      String billingKey=responseBody.get("billingKey").toString();
      EntrepreneurSubscribePaymentEntity entrepreneurSubscribePayment=
          EntrepreneurSubscribePaymentEntity.builder()
              .entrepreneurCode(entrepreneurEntity.getEntrepreneurCode())
              .entrepreneurSubscribePayment(SUBSCRIBE_PAY)
              .entrepreneurSubscribeBillingkey(billingKey)
              .build();
      System.out.println("저장시 문제"+entrepreneurSubscribePayment);
      System.out.println("저장시 문제"+entrepreneurEntity);

      String orderId=chargeBilling(billingKey,customerKey,SUBSCRIBE_PAY);
      System.out.println("orderId="+orderId);


      entrepreneurSubscribePayment.updateEntrepreneurSubscribePayment(
          LocalDateTime.now().plusDays(30),
          SUBSCRIBE_PAY,
          billingKey,
          orderId
      );
      entrepreneurSubscribePaymentRepository.save(entrepreneurSubscribePayment);
      System.out.println("저장 오류");

     // System.out.println(response.getBody());

      return "빌링키 발급 완료";

    } catch (Exception e) {
      Map<String, Object> error = new HashMap<>();
      error.put("error", e.getMessage());
      System.out.println("에러:"+e.getMessage());
      return "빌링키 생성 중 오류 발생";
    }
  }

  public Map<String,Object> BillingKeyCall(HttpEntity<Map<String, String>> entity) {

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Map> response = restTemplate.postForEntity(
        "https://api.tosspayments.com/v1/billing/authorizations/issue",
        entity,
        Map.class
    );
    return response.getBody();
  }


  @Transactional
  public String subscribePaymentCharge(
      String billingKey) {//유저아이디가 아닌 사업자 구별번호일듯
    EntrepreneurSubscribePaymentEntity entrepreneurSubscribePayment =
        entrepreneurSubscribePaymentRepository.findByEntrepreneurSubscribeBillingkey(billingKey);
    String customerKey = entrepreneurSubscribePayment.getEntrepreneur().getMember().getCustomerKey();
    Integer amount = entrepreneurSubscribePayment.getEntrepreneurSubscribePayment();

    String orderId=UUID.randomUUID().toString();

    HttpHeaders headers = new HttpHeaders();
    headers.setBasicAuth(SECRET_KEY, "");               // Authorization: Basic base64(secretKey:)
    headers.setContentType(MediaType.APPLICATION_JSON);

    Map<String, Object> body = new HashMap<>();
    body.put("customerKey", customerKey);//db에 저장된 유저 커스텀 키로 수정
    body.put("amount", amount);
    body.put("orderId", orderId);
    body.put("orderName", "구독 결제");

    Map<String, Object> toss = rest.postForObject(
        "https://api.tosspayments.com/v1/billing/" + billingKey,
        new HttpEntity<>(body, headers),
        Map.class
    );
    entrepreneurSubscribePayment.setEntrepreneurSubscribeEndAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")).plusDays(30));
//    entrepreneurSubscribePayment.setEntrepreneurSubscribeEndAt(LocalDateTime.now(ZoneId.of("Asia/Seoul")).plusSeconds(30));
    entrepreneurSubscribePayment.setEntrepreneurSubscribeOrderId(orderId);
    return "빌링키 발급 성공";

  }

  @Transactional
  public String deleteBillingKey(Long entrepreneurCode) {//유저아이디가 아닌 사업자 구별번호일듯

    String billingKey = entrepreneurSubscribePaymentRepository.findByEntrepreneurCode(
        entrepreneurCode).getEntrepreneurSubscribeBillingkey();
    String url = "https://api.tosspayments.com/v1/billing/"
        + billingKey;//https://api.tosspayments.com/v1/billing/{billingKey} url형태 설정

    HttpHeaders headers = new HttpHeaders();
    headers.setBasicAuth(SECRET_KEY, "");
    HttpEntity<Void> httpEntity = new HttpEntity<>(headers);
    try {
      rest.exchange(url, HttpMethod.DELETE, httpEntity, Void.class);//성공시 db삭제
      return "삭제 성공";
    } catch (Exception e) {
      return "삭제 실패";
    }
  }

  public ResponseEntity<String> subscribePaymentSelectByOrderId(Long entrepreneurCode) {
    String orderId = entrepreneurSubscribePaymentRepository.findById(entrepreneurCode).orElseThrow()
        .getEntrepreneurSubscribeOrderId();
    String url = "https://api.tosspayments.com/v1/payments/orders/" + orderId;
    String encodedAuth = Base64.getEncoder()
        .encodeToString((SECRET_KEY + ":").getBytes());
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Basic " + encodedAuth);

    HttpEntity<Map<String, Object>> request = new HttpEntity<>(headers);

    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<String> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        request,
        String.class
    );
    return response;
  }

  public ResponseEntity<String> subscribePaymentLogAll(LocalDateTime startDate,
      LocalDateTime endDate) {
    String url = "https://api.tosspayments.com/v1/transactions" +
        "?startDate=" + startDate + "&endDate=" + endDate;

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

  public Map<String, String> request(Long entrepreneurCode) {
    //String customerKey = UUID.randomUUID().toString();
    String customerKey=entrepreneurRepository.findById(entrepreneurCode).orElseThrow().getMember().getCustomerKey();
    Map<String, String> map = new HashMap<>();
    EntrepreneurSubscribePaymentEntity entrepreneurSubscribePayment = EntrepreneurSubscribePaymentEntity.builder()
        .entrepreneurCode(entrepreneurCode)
        //.entrepreneurSubscribeCustomerKey(customerKey)
        .build();
    entrepreneurSubscribePaymentRepository.save(entrepreneurSubscribePayment);
    map.put("customerKey", customerKey);
    return map;

  }
  // 매일 특정 시간에 실행 (예: 매일 01:00)
  @Transactional
  @Scheduled(cron = "0 0 01 * * ?",zone="Asia/Seoul")
  public void checkAndExecuteBilling() {
    System.out.println("빌링키 정기 구독 실행");
    ZoneId zone = ZoneId.of("Asia/Seoul");
    LocalDateTime start = LocalDate.now(zone).atStartOfDay(); // 00:00
    LocalDateTime end   = start.plusDays(1);

    // DB에서 오늘 결제해야 할 구독 목록 조회
    List<EntrepreneurSubscribePaymentEntity> SubscribePaymentDueToday =
        entrepreneurSubscribePaymentRepository.findByEntrepreneurSubscribeEndAtBetween(start,  end);

    for (EntrepreneurSubscribePaymentEntity subscribePayment : SubscribePaymentDueToday) {
      try {
        // 각 구독별로 자동결제 실행
        String orderId=chargeBilling(
            subscribePayment.getEntrepreneurSubscribeBillingkey(),
            //SubscribePayment.getEntrepreneurSubscribeCustomerKey(),
            subscribePayment.getEntrepreneur().getMember().getCustomerKey(),
            subscribePayment.getEntrepreneurSubscribePayment()
        );

        // 다음 결제일 계산 및 업데이트
        LocalDateTime nextSubscribeEndAt = LocalDateTime.now(ZoneId.of("Asia/Seoul")).plusDays(30);
        //LocalDateTime nextSubscribeEndAt = LocalDateTime.now(ZoneId.of("Asia/Seoul")).plusSeconds(30);
        subscribePayment.setEntrepreneurSubscribeEndAt(nextSubscribeEndAt);
        subscribePayment.setEntrepreneurSubscribeOrderId(orderId);

        entrepreneurSubscribePaymentRepository.save(subscribePayment);
        System.out.println("정기결제 성공");
      } catch (Exception e) {
        System.out.println("빌링키 정기결제 오류");
      }
    }
  }

  private String chargeBilling(String billingKey, String customerKey,
      Integer amount) {
    try {
      RestTemplate restTemplate = new RestTemplate();
      String orderId = UUID.randomUUID().toString();

      HttpHeaders headers = new HttpHeaders();
      headers.setBasicAuth(SECRET_KEY, "");               // Authorization: Basic base64(secretKey:)
      headers.setContentType(MediaType.APPLICATION_JSON);

      Map<String, Object> body = new HashMap<>();
      body.put("customerKey", customerKey);//db에 저장된 유저 커스텀 키로 수정
      body.put("amount", amount);
      body.put("orderId", orderId);
      body.put("orderName", "구독 결제");

      HttpEntity<Map<String, Object>> request =
          new HttpEntity<>(body, headers);

      restTemplate.postForEntity(
          "https://api.tosspayments.com/v1/billing/" + billingKey,
          request,
          String.class
      );

      return orderId;
    }catch (Exception e) {
      return e.getMessage();
    }
  }
}
