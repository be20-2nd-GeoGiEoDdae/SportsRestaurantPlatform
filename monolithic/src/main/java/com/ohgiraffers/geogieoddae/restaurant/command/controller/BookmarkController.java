package com.ohgiraffers.geogieoddae.restaurant.command.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.restaurant.command.service.BookmarkService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "즐겨찾기 관리 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookmark")
public class BookmarkController {
    private  final BookmarkService bookmarkService;

    @PostMapping("/{userId}/{restaurantId}")
    public ResponseEntity<ApiResponse<?>> registerBookmark(@PathVariable Long userId,
                                                           @PathVariable Long restaurantId){
        bookmarkService.registerBookmark(userId,restaurantId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<?>> deleteBookmark(@PathVariable Long userId){
        bookmarkService.deleteBookmark(userId);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

}
