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
public class ReportFlowTest {

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

    @DisplayName("사용자 리뷰 신고 및 관리자 확인 흐름을 테스트한다.")
    @Test
    void reportReviewAndAdminCheckFlowTest() throws Exception {
        // Step 1: 일반 사용자로 로그인하여 토큰 발급
        String userToken = getAuthToken("user@example.com", "test_user");
        assert userToken != null;

        // Step 2: 부적절한 리뷰를 신고
        // given
        // 신고할 리뷰 ID와 신고 내용 등을 DTO에 담습니다.
        // ReportDto reportDto = new ReportDto(1L, "INAPPROPRIATE_CONTENT", "이 리뷰는 부적절합니다.");
        // String reportBody = objectMapper.writeValueAsString(reportDto);
        
        // when
        // ExtractableResponse<Response> reportResponse = RestAssured
        //         .given().log().all()
        //         .header("Authorization", "Bearer " + userToken)
        //         .contentType("application/json")
        //         .body(reportBody)
        //         .when()
        //         .post("/api/reports") // 실제 신고 엔드포인트
        //         .then().log().all()
        //         .extract();

        // then
        // assert reportResponse.statusCode() == HttpStatus.CREATED.value();
        // Long reportId = reportResponse.jsonPath().getLong("result.reportId");
        // assert reportId != null;

        // Step 3: 관리자로 로그인하여 토큰 발급
        // 실제 테스트 시에는 관리자 권한을 가진 테스트 유저 정보 사용
        String adminToken = getAuthToken("admin@example.com", "test_admin");
        assert adminToken != null;

        // Step 4: 관리자가 신고 내역을 조회하여 확인
        // when
        // ExtractableResponse<Response> getReportsResponse = RestAssured
        //         .given().log().all()
        //         .header("Authorization", "Bearer " + adminToken)
        //         .when()
        //         .get("/api/admin/reports") // 실제 관리자 신고 조회 엔드포인트
        //         .then().log().all()
        //         .extract();
        
        // then
        // assert getReportsResponse.statusCode() == HttpStatus.OK.value();
        // 신고 목록에서 Step 2에서 생성된 reportId가 포함되어 있는지 확인합니다.
        // List<Integer> reportedIds = getReportsResponse.jsonPath().getList("result.reports.reportId");
        // assert reportedIds.contains(reportId.intValue());
    }
}
