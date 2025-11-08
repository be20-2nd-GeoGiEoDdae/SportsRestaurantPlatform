package com.ohgiraffers.geogieoddae.restaurant.command.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.restaurant.command.dto.KeywordDto;
import com.ohgiraffers.geogieoddae.restaurant.command.service.KeywordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "키워드 관리 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants/keyword")
public class KeywordController {

    private final KeywordService keywordService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<KeywordDto>> createKeyword(@RequestBody KeywordDto keywordDTO) {
            keywordService.keyWordCreate(keywordDTO);

            return ResponseEntity.ok(ApiResponse.success(keywordDTO));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteKeyword(@PathVariable Long id) {

            keywordService.keyWordRemove(id);
            return ResponseEntity.ok(ApiResponse.success(null));

    }
}
