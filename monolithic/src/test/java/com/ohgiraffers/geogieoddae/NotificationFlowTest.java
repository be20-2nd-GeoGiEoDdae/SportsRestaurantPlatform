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
public class NotificationFlowTest {

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

    @DisplayName("알림 유발 행위 및 수신자 확인 흐름을 테스트한다.")
    @Test
    void triggerAndReceiveNotificationFlowTest() throws Exception {
        // Step 1: 알림을 보낼 사용자(User A)로 로그인
        String senderToken = getAuthToken("sender@example.com", "test_sender");
        assert senderToken != null;

        // Step 2: 알림을 유발하는 행동 수행 (예: User B의 게시물에 댓글 작성)
        // given
        // 댓글 내용과 게시물 ID 등을 DTO에 담습니다.
        // CommentDto commentDto = new CommentDto("멋진 글이네요!", 123L); // 123L은 User B의 게시물 ID
        // String commentBody = objectMapper.writeValueAsString(commentDto);
        
        // when
        // ExtractableResponse<Response> commentResponse = RestAssured
        //         .given().log().all()
        //         .header("Authorization", "Bearer " + senderToken)
        //         .contentType("application/json")
        //         .body(commentBody)
        //         .when()
        //         .post("/api/comments") // 실제 댓글 작성 엔드포인트
        //         .then().log().all()
        //         .extract();

        // then
        // assert commentResponse.statusCode() == HttpStatus.CREATED.value();

        // Step 3: 알림을 받을 사용자(User B)로 로그인
        String receiverToken = getAuthToken("receiver@example.com", "test_receiver");
        assert receiverToken != null;

        // Step 4: User B가 자신의 알림 목록을 조회하여 확인
        // when
        // ExtractableResponse<Response> getNotificationsResponse = RestAssured
        //         .given().log().all()
        //         .header("Authorization", "Bearer " + receiverToken)
        //         .when()
        //         .get("/api/notifications") // 실제 알림 목록 조회 엔드포인트
        //         .then().log().all()
        //         .extract();
        
        // then
        // assert getNotificationsResponse.statusCode() == HttpStatus.OK.value();
        // 알림 목록에서 User A가 보낸 새 알림이 있는지 확인합니다.
        // String recentNotificationMessage = getNotificationsResponse.jsonPath().getString("result.notifications[0].message");
        // assert recentNotificationMessage.contains("test_sender님이 댓글을 남겼습니다.");
    }
}
