package com.ohgiraffers.geogieoddae.restaurant.command.controller;

import com.ohgiraffers.geogieoddae.global.common.dto.ApiResponse;
import com.ohgiraffers.geogieoddae.restaurant.command.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController {
    private  final BookmarkService bookmarkService;
    @PostMapping("/{userId}/{restaurantId}")
    public ResponseEntity<ApiResponse<?>> registerBookmark(@PathVariable Long userId,
                                                           @PathVariable Long restaurantId){
        bookmarkService.registerBookmark(userId,restaurantId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
    @DeleteMapping("/{userId}/{restaurantId}")
    public ResponseEntity<ApiResponse<?>> deleteBookmark(@PathVariable Long userId,
                                                         @PathVariable Long restaurantId){
        bookmarkService.deleteBookmark(userId);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

}
