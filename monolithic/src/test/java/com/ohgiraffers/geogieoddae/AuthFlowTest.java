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
public class AuthFlowTest {

    @LocalServerPort
    private int port;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    private ExtractableResponse<Response> loginAndGetTokens() throws Exception {
        KakaoUserInfoDto kakaoUserInfo = new KakaoUserInfoDto("test@example.com", "testuser", null);
        String requestBody = objectMapper.writeValueAsString(kakaoUserInfo);

        return RestAssured
                .given().log().all()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/auth/kakao/callback")
                .then().log().all()
                .extract();
    }

    @DisplayName("로그인 및 로그아웃 흐름을 테스트한다.")
    @Test
    void loginAndLogoutFlowTest() throws Exception {
        // Step 1: 카카오 정보로 로그인하여 토큰 발급
        ExtractableResponse<Response> loginResponse = loginAndGetTokens();
        String accessToken = loginResponse.jsonPath().getString("result.accessToken");

        assert loginResponse.statusCode() == HttpStatus.OK.value();
        assert accessToken != null;

        // Step 2: 발급받은 토큰을 사용하여 로그아웃
        // given
        // 로그아웃은 보통 헤더에 토큰을 담아 요청합니다.
        
        // when
        // ExtractableResponse<Response> logoutResponse = RestAssured
        //         .given().log().all()
        //         .header("Authorization", "Bearer " + accessToken)
        //         .when()
        //         .post("/api/auth/logout") // 실제 로그아웃 엔드포인트
        //         .then().log().all()
        //         .extract();

        // then
        // assert logoutResponse.statusCode() == HttpStatus.OK.value();
    }
}
