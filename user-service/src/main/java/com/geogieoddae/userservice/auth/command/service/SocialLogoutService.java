package com.geogieoddae.userservice.auth.command.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SocialLogoutService {

    public void logoutKakao(String accessToken) {
        // accessToken에서 "Bearer " 제거
        String token = accessToken.replace("Bearer ", "");

        // 카카오 로그아웃 API 호출
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = "https://kapi.kakao.com/v1/user/logout";
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
            // 성공/실패 로그 처리
        } catch (Exception e) {
            // 예외 처리
            throw new RuntimeException("카카오 로그아웃 실패", e);
        }
    }
}