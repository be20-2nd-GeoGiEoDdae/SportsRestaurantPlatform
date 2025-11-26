package com.ohgiraffers.geogieoddae.viewing.query.controller;

import com.ohgiraffers.geogieoddae.viewing.query.dto.ViewingDto;
import com.ohgiraffers.geogieoddae.viewing.query.dto.ViewingPictureDto;
import com.ohgiraffers.geogieoddae.viewing.query.service.ViewingQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "관람 조회 api")
@RestController
@RequestMapping("/api/viewings")
@RequiredArgsConstructor
@Slf4j
public class ViewingQueryController {

    private final ViewingQueryService viewingQueryService;
    @GetMapping("/{viewingCode}/simple")
    public Map<String, Object> getViewingSimple(@PathVariable Long viewingCode) {

        ViewingPictureDto dto = viewingQueryService.findViewingDetail(viewingCode);

        if (dto == null) {
            return null;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("viewingTitle", dto.getViewingTitle());
        result.put("restaurantName", dto.getRestaurantName());
        result.put("sportName", dto.getSportName());
        result.put("teamName", dto.getTeamName());
        result.put("pictureUrls", dto.getPictureUrls()); // ⭐ 추가

        return result;
    }
    // 검색
    @GetMapping("/search")
    public List<ViewingDto> searchViewings(@RequestParam String keyword) {
        return viewingQueryService.searchViewings(keyword);
    }

    // ⭐ 거리 포함 목록 조회
    @GetMapping
    public ResponseEntity<?> getViewings(
            @RequestParam(required = false) Double lat,
            @RequestParam(required = false) Double lng,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,

            // ⭐ 추가: 음식 카테고리
            @RequestParam(required = false) String category,

            // ⭐ 추가: 키워드 (여러 개)
            @RequestParam(required = false) List<String> keywords,

            // ⭐ 추가: 정렬 기준(distance, score, name)
            @RequestParam(required = false, defaultValue = "distance") String sort
    ) {

        Page<ViewingDto> result =
                viewingQueryService.findAllViewings(lat, lng, page, size, category, keywords, sort);

        return ResponseEntity.ok(result);
    }



    // 조건별 조회
    @GetMapping("/condition")
    public List<ViewingDto> findByCondition(
            @RequestParam(required = false) String teamName,
            @RequestParam(required = false) String restaurantName,
            @RequestParam(required = false) String sportName) {

        Map<String, Object> conditions = new HashMap<>();
        conditions.put("teamName", teamName);
        conditions.put("restaurantName", restaurantName);
        conditions.put("sportName", sportName);

        return viewingQueryService.findViewingsByCondition(conditions);
    }

    // 상세 조회
    @GetMapping("/{viewingCode}")
    public ViewingPictureDto findViewingDetail(@PathVariable Long viewingCode) {
        return viewingQueryService.findViewingDetail(viewingCode);
    }
}
