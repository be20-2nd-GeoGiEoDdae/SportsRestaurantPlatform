package com.geogieoddae.mainservice.viewing.query.controller;

import com.geogieoddae.mainservice.viewing.query.dto.ViewingDto;
import com.geogieoddae.mainservice.viewing.query.dto.ViewingPictureDto;
import com.geogieoddae.mainservice.viewing.query.service.ViewingQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viewings")
@RequiredArgsConstructor
public class ViewingQueryController {

    private final ViewingQueryService viewingQueryService;

    // 검색
    @GetMapping("/search")
    public List<ViewingDto> searchViewings(@RequestParam String keyword) {
        return viewingQueryService.searchViewings(keyword);
    }

    // 목록 조회
    @GetMapping
    public List<ViewingDto> findAllViewings() {
        return viewingQueryService.findAllViewings();
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
