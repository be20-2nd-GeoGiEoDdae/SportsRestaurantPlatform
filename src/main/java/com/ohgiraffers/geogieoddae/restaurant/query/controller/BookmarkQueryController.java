package com.ohgiraffers.geogieoddae.restaurant.query.controller;

import com.ohgiraffers.geogieoddae.restaurant.query.service.BookmarkQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookmarks")
@RequiredArgsConstructor
public class BookmarkQueryController {

    private final BookmarkQueryService bookmarkQueryService;

    @GetMapping("/{userCode}")
    public ResponseEntity<List<?>> getReviewsByRestaurant(@PathVariable Long userCode) {
        return ResponseEntity.ok(bookmarkQueryService.getBookmarksByUser(userCode));
    }
}
