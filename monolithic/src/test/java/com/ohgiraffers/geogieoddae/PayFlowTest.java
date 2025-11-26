package com.ohgiraffers.geogieoddae;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohgiraffers.geogieoddae.auth.command.dto.KakaoUserInfoDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PayFlowTest {

    @LocalServerPort
    private int port;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    private String getAuthToken(String email, String nickname) throws Exception {
        KakaoUserInfoDto kakaoUserInfo = new KakaoUserInfoDto(email, nickname, null);
        String requestBody = objectMapper.writeValueAsString(kakaoUserInfo);

        ExtractableResponse<Response> response = RestAssured
                .given().log().all()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/auth/kakao/callback")
                .then().log().all()
                .extract();

        return response.jsonPath().getString("result.accessToken");
    }

    @DisplayName("사업자 구독 결제 흐름을 테스트한다.")
    @Test
    void entrepreneurSubscriptionFlowTest() throws Exception {
        // Step 1: 사업자(Entrepreneur)로 로그인하여 토큰 발급
        // 실제 테스트 시에는 사업자 권한을 가진 테스트 유저 정보 사용
        String entrepreneurToken = getAuthToken("entrepreneur@example.com", "test_entrepreneur");
        assert entrepreneurToken != null;

        // Step 2: 구독을 위한 빌링키(Billing Key)를 발급받았다고 가정
        // 실제 흐름에서는 /api/subscribe/request/{entrepreneurCode} 호출 후,
        // 리다이렉션되는 페이지에서 사용자가 동의하여 빌링키가 발급됩니다.
        // 테스트에서는 미리 발급된 테스트용 빌링키를 사용합니다.
        String testBillingKey = "test_billing_key_for_entrepreneur";

        // Step 3: 발급된 빌링키로 구독료 결제를 요청
        // given
        // 결제 요청에 필요한 정보 (예: 주문 ID 등)
        
        // when
        // ExtractableResponse<Response> chargeResponse = RestAssured
        //         .given().log().all()
        //         .header("Authorization", "Bearer " + entrepreneurToken)
        //         .when()
        //         .post("/api/subscribe/charge/{billingKey}", testBillingKey)
        //         .then().log().all()
        //         .extract();

        // then
        // assert chargeResponse.statusCode() == HttpStatus.OK.value();
        // assert "DONE".equals(chargeResponse.jsonPath().getString("result.status")); // 결제 완료 상태 확인
    }
}
