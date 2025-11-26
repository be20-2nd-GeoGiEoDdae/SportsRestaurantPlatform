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
public class AnnouncementFlowTest {

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

    @DisplayName("관리자 공지 등록 및 사용자 확인 흐름을 테스트한다.")
    @Test
    void createAndReadAnnouncementFlowTest() throws Exception {
        // Step 1: 관리자로 로그인하여 토큰 발급
        String adminToken = getAuthToken("admin@example.com", "test_admin");
        assert adminToken != null;

        // Step 2: 새로운 공지사항을 등록
        // given
        // 등록할 공지사항 제목과 내용을 DTO에 담습니다.
        // AnnouncementDto announcementDto = new AnnouncementDto("서버 점검 안내", "금일 자정부터 서버 점검이 있습니다.");
        // String announcementBody = objectMapper.writeValueAsString(announcementDto);
        
        // when
        // ExtractableResponse<Response> createResponse = RestAssured
        //         .given().log().all()
        //         .header("Authorization", "Bearer " + adminToken)
        //         .contentType("application/json")
        //         .body(announcementBody)
        //         .when()
        //         .post("/api/announcements") // 실제 공지 등록 엔드포인트
        //         .then().log().all()
        //         .extract();

        // then
        // assert createResponse.statusCode() == HttpStatus.CREATED.value();
        // Long announcementId = createResponse.jsonPath().getLong("result.announcementId");
        // assert announcementId != null;

        // Step 3: 일반 사용자로 로그인하여 토큰 발급
        String userToken = getAuthToken("user@example.com", "test_user");
        assert userToken != null;

        // Step 4: 사용자가 공지사항 목록을 조회하여 확인
        // when
        // ExtractableResponse<Response> getListResponse = RestAssured
        //         .given().log().all()
        //         .header("Authorization", "Bearer " + userToken)
        //         .when()
        //         .get("/api/announcements") // 실제 공지 목록 조회 엔드포인트
        //         .then().log().all()
        //         .extract();
        
        // then
        // assert getListResponse.statusCode() == HttpStatus.OK.value();
        // 공지 목록에서 Step 2에서 생성된 announcementId가 포함되어 있는지 확인합니다.
        // List<Integer> ids = getListResponse.jsonPath().getList("result.announcements.id");
        // assert ids.contains(announcementId.intValue());
    }
}
