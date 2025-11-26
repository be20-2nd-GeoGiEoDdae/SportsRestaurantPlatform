package com.ohgiraffers.geogieoddae;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserEntity;
import com.ohgiraffers.geogieoddae.auth.command.entity.user.UserRole;
import com.ohgiraffers.geogieoddae.auth.command.repository.UserRepository;
import com.ohgiraffers.geogieoddae.global.jwt.JwtTokenProvider;
import com.ohgiraffers.geogieoddae.pay.command.dto.ViewingPayRequest;
import com.ohgiraffers.geogieoddae.viewing.command.dto.ViewingUserDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ViewingFlowTest {
    // 관람 신청부터 결제까지
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @LocalServerPort
    private int port;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // 테스트에서 사용할 값들
    private String userCode;
    private String userRole;
    private String authToken;
    private final Long viewingCode = 1L;

    @BeforeEach
    void setUp() {

        RestAssured.port = port;

        // 1) 테스트용 더미 유저 생성
        UserEntity user = UserEntity.builder()
                .userEmail("dummy@test.com")
                .userName("tester")
                .userPhoneNumber("01000000000")
                .userRole(UserRole.USER)
                .build();

        userRepository.save(user);

        userCode = String.valueOf(user.getUserCode());
        userRole = String.valueOf(user.getUserRole());
        // 2) JWT 토큰 생성 (카카오 로그인 대신)
        authToken = jwtTokenProvider.generateUserAccessToken(userCode, userRole);
    }

    @DisplayName("로그인 후, 관람 신청부터 결제 시작까지의 흐름을 테스트한다.")
    @Test
    void applyAndInitiatePaymentForViewingFlowTest() throws Exception {

        // Step 1: 관람 신청
        ExtractableResponse<Response> applyResponse = attemptToApplyForViewing(authToken);

        Assertions.assertEquals(HttpStatus.OK.value(), applyResponse.statusCode());

        // Step 2: 관람 결제 시작
        ExtractableResponse<Response> paymentResponse = initiatePaymentForViewing(authToken);

        Assertions.assertEquals(HttpStatus.FOUND.value(), paymentResponse.statusCode());
        Assertions.assertTrue(paymentResponse.header("Location").contains("/viewingPay/index/"));
    }

    private ExtractableResponse<Response> attemptToApplyForViewing(String token) throws Exception {

        ViewingUserDto dto = new ViewingUserDto();
        dto.setUserCode(Long.valueOf(userCode));
        dto.setViewingUserDeposit(10000);

        String body = objectMapper.writeValueAsString(dto);

        return RestAssured
                .given().log().all()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(body)
                .when()
                .post("/api/viewings/{viewingCode}/apply", viewingCode)
                .then().log().all()
                .extract();
    }

    private ExtractableResponse<Response> initiatePaymentForViewing(String token) throws Exception {

        ViewingPayRequest dto = new ViewingPayRequest();
        dto.setViewingCode(viewingCode);
        dto.setUserCode(Long.valueOf(userCode));
        dto.setViewingPayPrice(50000L);

        String body = objectMapper.writeValueAsString(dto);

        return RestAssured
                .given().log().all()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(body)
                .when()
                .post("/api/viewingPay/info")
                .then().log().all()
                .extract();
    }
}
