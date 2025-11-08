package com.ohgiraffers.geogieoddae.global.apikey;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoogleGeocodingService {

    @Value("${google.api.key}")
    private String googleApiKey;

    private static final String GOOGLE_URL_TEMPLATE =
            "https://maps.googleapis.com/maps/api/geocode/json?%s";

    @PostConstruct
    public void init() {
        log.info("🔑 Google API Key loaded: {}", googleApiKey != null ? "✅ 로드됨" : "❌ (NULL)");
    }

    public double[] getCoordinates(String address) {
        if (googleApiKey == null || googleApiKey.isBlank()) {
            log.error("❌ Google API key가 주입되지 않았습니다.");
            return new double[]{0, 0};
        }

        try {
            // ✅ 도로명 주소 권장 — 구주소일 경우 정확도 떨어질 수 있음
            String fullAddress = address + ", South Korea";
            String encoded = URLEncoder.encode(fullAddress, StandardCharsets.UTF_8);

            // ✅ 요청 URL 구성 (국가 및 지역 지정)
            String params = String.format(
                    "address=%s&language=ko&components=locality:Seoul|country:KR&key=%s",
                    encoded, googleApiKey
            );
            String url = String.format(GOOGLE_URL_TEMPLATE, params);

            log.info("📨 Google 요청 URL: {}", url);

            // ✅ API 요청
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            if (response.getBody() == null) {
                log.error("⚠️ Google 응답이 null 입니다.");
                return new double[]{0, 0};
            }

            String status = (String) response.getBody().get("status");
            List<Map<String, Object>> results = (List<Map<String, Object>>) response.getBody().get("results");

            if (!"OK".equals(status) || results == null || results.isEmpty()) {
                log.warn("⚠️ '{}' 주소 검색 실패 (status={})", address, status);
                return new double[]{0, 0};
            }

            Map<String, Object> geometry = (Map<String, Object>) results.get(0).get("geometry");
            Map<String, Object> location = (Map<String, Object>) geometry.get("location");

            double lat = ((Number) location.get("lat")).doubleValue();
            double lon = ((Number) location.get("lng")).doubleValue();

            // ✅ 대한민국 기본 좌표(실패 시 리턴되는 값) 감지 후 실패 처리
            if (lat == 35.907757 && lon == 127.766922) {
                log.warn("⚠️ '{}' → 기본 좌표 반환됨 (실패 처리)", address);
                return new double[]{0, 0};
            }

            log.info("✅ '{}' → 위도: {}, 경도: {}", address, lat, lon);
            return new double[]{lat, lon};

        } catch (Exception e) {
            log.error("❌ 주소 '{}' 변환 중 예외 발생: {}", address, e.getMessage(), e);
            return new double[]{0, 0};
        }
    }
}
