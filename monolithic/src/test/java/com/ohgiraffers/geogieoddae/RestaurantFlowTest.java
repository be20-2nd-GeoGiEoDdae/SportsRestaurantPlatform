package com.ohgiraffers.geogieoddae;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.RestaurantDto;
import com.ohgiraffers.geogieoddae.restaurant.command.entity.restaurant.RestaurantCategory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestaurantFlowTest {

    @LocalServerPort
    private int port;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {


        RestAssured.port = port;
    }

    @DisplayName("점주가 가게를 등록한다.")
    @Test
    @WithMockUser(authorities = "ROLE_ENTREPRENEUR")
    void registerRestaurantTest() throws JsonProcessingException {
        RestaurantDto restaurantDto = new RestaurantDto(
                "테스트 가게",
                "서울시 강남구",
                RestaurantCategory.KOREAN,
                10,
                "가게 설명입니다.",
                0, // 초기 score
                1L, // 점주 코드
                Collections.singletonList(1L), // 키워드 ID
                null // pictures는 파일 업로드로 처리되므로 DTO에서는 null
        );
        String restaurantJson = objectMapper.writeValueAsString(restaurantDto);


        RestAssured.given()
                .log().all()
                .contentType(ContentType.MULTIPART)
                .multiPart("restaurant", restaurantJson, "application/json")
                // .multiPart("pictures", new File("path/to/your/image.jpg")) // 파일 첨부가 필요하다면 이 부분을 활성화
                .when()
                       .post("/api/restaurants")
                .then()
                       .log().all()
                       .assertThat()
                       .statusCode(HttpStatus.OK.value()); // 컨트롤러에서 200 OK를 반환하므로
    }
}
