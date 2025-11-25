package com.ohgiraffers.geogieoddae.restaurant.query.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.restaurant.query.dto.KeywordDto;
import com.ohgiraffers.geogieoddae.restaurant.query.service.KeywordQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/keywords")
public class KeywordQueryController {

    private final KeywordQueryService keywordQueryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<KeywordDto>>> getKeywords() {
        List<KeywordDto> keywords = keywordQueryService.getKeywordList();
        return ResponseEntity.ok(ApiResponse.success(keywords));
    }
}
